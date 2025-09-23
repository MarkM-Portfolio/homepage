/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2010, 2022                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.web.controller.palette;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.FINEST;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.lconn.core.services.cre.remote.widget.model.Widget;
import com.ibm.lconn.core.services.cre.remote.widget.model.WidgetCategory;
import com.ibm.lconn.homepage.model.Category;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.web.controller.AbstractWidgetController;
import com.ibm.lconn.homepage.web.controller.ViewConstants;
import com.ibm.lconn.homepage.web.utils.IUrlNormalizer;
import com.ibm.lconn.homepage.web.WebException;

//TODO: need to review logic here, we should not have to pass the same
// widget list in 2 different forms

@Controller
@RequestMapping(value = { "/web" })
public class PaletteConfigController extends AbstractWidgetController {
	private static final long serialVersionUID = -6366585064371425488L;

	private final static String CLASS_NAME = PaletteConfigController.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	private List<Widget> availWidgets;
	private List<WidgetCategory> categoriesTree;

	@Autowired
	private IUrlNormalizer urlNormalizer;

	public String executeInternal(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "execute");

		// CNXSERV-17221 prevent SQL errors for invalid params
		if (StringUtils.isEmpty(getTabInstanceId()) || getTabInstanceId().length() > 36) {
				String error = getWebResourceBundle().getString("error.admin.retrieve", getTabInstanceId());
                throw new WebException(error);
		}
		
		// Obtain the user preferences factory. Indicate if the user is in
		// an administrator role.
		boolean showDisabledWidgets = isShowDisabledWidgets();
		if (logger.isLoggable(FINEST) && showDisabledWidgets) {
			logger.logp(FINEST, CLASS_NAME, "execute",
					"Showing disabled widgets");
		}

		final Locale locale = getLocale(request);

		List<Widget> availWidgets = getWidgetServices()
				.getAvailableWidgetsByTabInstanceId(getTabInstanceId(), locale,
						showDisabledWidgets);

		if (availWidgets == null) {
			availWidgets = new ArrayList<Widget>();
		}

		sortAvailWidgets(availWidgets, locale);

		// Obtain widget category tree, ready to be transformed to JSON to be
		// consumed by widget palette
		Map<String, WidgetCategory> widgetsTree = getWidgetServices().getAvailableWidgetsTree(getTabInstanceId(), locale, showDisabledWidgets);

		if (widgetsTree == null) {
			widgetsTree = new HashMap<String, WidgetCategory>();
		}

		boolean isSecure = request.isSecure();
		
		urlNormalizer.translateUrls(availWidgets, isSecure);
		urlNormalizer.translateUrls(widgetsTree, isSecure);

		List<WidgetCategory> categoriesTree = new ArrayList<WidgetCategory>(
				widgetsTree.values());

		List<WidgetCategory> sortedCategoriesTree = null;

		if (categoriesTree != null) {
			// use a tree map to sort by alphabetical order (in english) for BVT
			// test scripts

			sortedCategoriesTree = sortWidgetCategories(categoriesTree,
					locale);
		}

		setAvailWidgets(availWidgets);
		setCategoriesTree(sortedCategoriesTree);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "execute", "success");

		addPagedAttributesToModel(model, request);
        return ViewConstants.VIEW_HOMEPAGE_PALETTE_JSONWIDGETCATALOGPAGE;

	}

	@RequestMapping(value = { "/getPaletteConfig.action" }, method = RequestMethod.GET)
    public String execute(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "tabInstanceId", required = true) String tabInstanceId) throws Exception {

        init(request);
        setTabInstanceId(tabInstanceId);

        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "execute");

        try {
            return executeInternal(model, request, response);
        } catch (ServiceException se) {
            logger.log(Level.SEVERE, se.getMessage(), se);
            response.sendError(500, se.getErrorCode());
            return ViewConstants.VIEW_ERROR;
        } catch (Throwable t) {
            logger.log(Level.SEVERE, t.getMessage(), t);
            response.sendError(500, t.getMessage());
            return ViewConstants.VIEW_ERROR;
        } finally {
            if (logger.isLoggable(FINER))
                logger.exiting(CLASS_NAME, "execute");
        }

    }
	
	public List<Widget> getAvailWidgets() {
		return availWidgets;
	}

	public void setAvailWidgets(List<Widget> availWidgets) {
		this.availWidgets = availWidgets;
	}

	public List<WidgetCategory> getCategoriesTree() {
		return categoriesTree;
	}

	public void setCategoriesTree(List<WidgetCategory> sortedCategoryTree) {
		this.categoriesTree = sortedCategoryTree;
	}

	private List<WidgetCategory> sortWidgetCategories(
			List<WidgetCategory> categoriesTree, Locale locale) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "sortWidgetList",
					new Object[] { categoriesTree });
		Collections.sort(categoriesTree, getCategoryComparator(locale));

		for (WidgetCategory cat : categoriesTree) {
			cat.sortWidgets(getComparator(locale));
		}

		return categoriesTree;
	}

	private Comparator<WidgetCategory> getCategoryComparator(Locale locale) {
		final Collator collator = Collator.getInstance(locale);

		// order by alphabetic order, EXCEPT for the Other category (3rd party)
		// at the end
		return new Comparator<WidgetCategory>() {
			public int compare(WidgetCategory cat1, WidgetCategory cat2) {
				if (cat1 == null)
					return -1;
				else if (cat2 == null)
					return 1;
				else if (cat1.getId().equals(Category.OTHER.name()))
					return 1;
				else if (cat2.getId().equals(Category.OTHER.name()))
					return -1;
				else
					return collator.compare(cat1.getName(), cat2.getName());
			};
		};
	}

	private Comparator<Widget> getComparator(Locale locale) {
		final Collator collator = Collator.getInstance(locale);

		return new Comparator<Widget>() {
			public int compare(Widget bean1, Widget bean2) {
				String title1 = bean1.getTitle();
				String title2 = bean2.getTitle();

				if (title1 == null)
					return -1;
				else if (title2 == null)
					return 1;
				else
					return collator.compare(title1, title2);
			};
		};
	}

	private void sortAvailWidgets(List<Widget> beans, Locale locale) {
		// Sort by alphabetical order
		Collections.sort(beans, getComparator(locale));
	}

	public IUrlNormalizer getUrlNormalizer() {
		return urlNormalizer;
	}

	public void setUrlNormalizer(IUrlNormalizer urlNormalizer) {
		this.urlNormalizer = urlNormalizer;
	}
	
	protected void addPagedAttributesToModel(Model model, HttpServletRequest request) {
        // set pagination values
        model.addAttribute("availWidgets", getAvailWidgets());
        model.addAttribute("categoriesTree", getCategoriesTree());

        super.addPagedAttributesToModel(model);
    }

}

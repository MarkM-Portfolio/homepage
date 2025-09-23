/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2009, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("lconn.homepage.core.constants.DojoEvents");


lconn.homepage.events = {

    /*
     * Activity Events
     *
     */
    activitiesCalendar: {
        DAY_FLAG: "/activitiesCalendar/dayFlag"
    },
    
    /*
     * DnD Events
     *
     */
    dnd: {
        DROP: "/lconn/homepage/core/dnd/drop",
        CANCEL: "/dnd/cancel"
    },
    
    /* 
     * Initialisation Events
     *
     */
    init: {
        COMPLETED: "/lconn/dboard/init/completed"
    },
    
    /* 
     * Keyboard Events
     *
     */
    keyboard: {
        river: {
            NEXT: "/lconn/dboard/keyboardController/river/next",
            PREV: "/lconn/dboard/keyboardController/river/next"
        }
    },
    
    /*
     * Layout Events
     *
     */
    layout: {
        UPDATED: "lconn/homepage/layout/updated",
        RESIZE_WIDTH: "onResize"
    },
    
    /*
     * Navigation Events
     */
    navigation: {
        ITEM_SELECTED: "lconn/homepage/navigation/itemSelected"
    },
    
    /*
     * Palette Events
     *
     */
    palette: {
        DROP_ITEM: "/lconn/dboard/dropPaletteItem",
        RECHECK: "/lconn/core/palette/recheck",
        WIDGET_CREATED: "lconn/dboard/palette/widgetCreated",
        TOGGLE: "/lconn/dboard/palette/togglePaletteEvent"
    },
    
    popup: {
        DESTROY: "/lconn/dboard/popupWidget/Destroy"
    },
    
    river: {
		/**
		 * One of the component filter at the top has been clicked
		 */
        FILTER: "lconn/dboard/river/filterBy",
		
		footer: {
			/*
			HIDE: "/lconn/dboard/river/stories/hidePager",
        	SHOW: "/lconn/dboard/river/stories/showPager", */
			/**
			 * The action "Show more stories" has been clicked
			 */
			ON_MORE_STORIES: "/lconn/homepage/river/stories/onMoreStories"
		},
		
		storiesContainer: {
			/**
			 * Story container triggered an Ajax request for new stories
			 */
			ON_START_FETCHING: "/lconn/honepage/river/stories/onStartFetching",
			/**
			 * Request for new stories returned (success or error)
			 */
			ON_STOP_FETCHING: "/lconn/honepage/river/stories/onStopFetching",
			/**
			 * The content displayed by the container has been updated
			 */
			ON_STORIES_CONTAINER_UPDATED: "/lconn/homepage/river/stories/onStoriesContainerUpdated"
		},		
        
		/**
		 * User has resized his browser window.
		 */
        REFRESH: "/lconn/dboard/river/stories/refresh"
    },
    
    /*
     * Tab Events
     *
     */
    tabs: {
        TAB_CLICKED: "/lconn/dboard/tabs/tabClicked",
        TAB_MENU_CLICKED: "/lconn/dboard/tabs/tabMenuClicked",
        PAGE_EVENT: "/lconn/dboard/tabs/TabContentPane/pageEvent"
    },
    
    /*
     * Widget Events
     *
     */
    widget: {
        LOADED: "/lconn/homepage/core/iwidget/IWidgetWrapper/widgetLoaded",
        DESTROY: "destroyWidget"
    }
};


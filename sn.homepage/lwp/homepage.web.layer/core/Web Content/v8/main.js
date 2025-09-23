/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited 2021, 2022                     */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

// make sure outter object is available
com.hcl = com.hcl || {};
com.hcl.connections = com.hcl.connections || {};
com.hcl.connections.homepage = com.hcl.connections.homepage || {};

// define homepage v8 functionality
com.hcl.connections.homepage.v8 = {

    log : (msg, method) => {
        if (dojo.config.isDebug) {
            let logStr = 'com.hcl.connections.homepage.v8'
            if (method.length) {
                logStr += ':' + method
            }
            logStr += ' ' + msg;
            console.log(logStr);
        }
    },

    // function to render the cnx-react-components/content-filter component into the ActivityStream UI
    // - this integrates itself into the existing implementation, invoking dojo functionality internally
    renderContentFilterComponent : async () => {
        // remove existing component
        let _customContentFilterWrapper = document.querySelector('#custom_stream_content_filter');
        if (_customContentFilterWrapper) {
            _customContentFilterWrapper.parentElement.removeChild(_customContentFilterWrapper);
        }
        _customContentFilterWrapper = document.createElement('div');
        _customContentFilterWrapper.id = 'custom_stream_content_filter';

        const _foundFilterArea = await window.ui._dom_element_is_available('.filterAreaInner .lotusRight');
        if (!_foundFilterArea) {
            return;
        }
        const _filterArea = document.querySelector('.filterAreaInner .lotusRight');
        _filterArea.prepend(_customContentFilterWrapper);

        // gather existing filter options
        const _foundFilter = await window.ui._dom_element_is_available('.filterAreaInner .asFilterMenu select');
        if (!_foundFilter) {
            return;
        }
        const _filter = document.querySelector('.filterAreaInner .asFilterMenu select');
        let _options = [];
        const _selectedIdx = _filter.selectedIndex;
        for (let i=0; i< _filter.options.length; i++) {
            _options.push(_filter.options[i].text);
        };

        const _handleContentFilterChange = (selectedIdx) => {
            // fetch underlying activity stream filter menu again to get up to date event handles (as this has been rerendered by dojo)
            const _filter = document.querySelector('.filterAreaInner .asFilterMenu select');
            const evt = new Event('change');
            _filter.selectedIndex = selectedIdx;
            _filter.dispatchEvent(evt);
            com.hcl.connections.homepage.v8.renderTagsFilterComponent();
        }

        // wait for loadReactApp to become available
        const _isReactAvailable = await window.ui._is_react_available('com.ibm.ics.apps.loadReactApp');
        if (_isReactAvailable) {
            com.ibm.ics.apps.loadReactApp(
                'cnx-react-components',
                'content-filter',
                _customContentFilterWrapper.id,
                Object.assign({
                    options: _options,
                    selectedOption: _selectedIdx,
                    handleChange: _handleContentFilterChange
                }),
            );
        }
        com.hcl.connections.homepage.v8.renderTagsFilterComponent();
    },

    // function to render the cnx-react-components/tags-filter component into the ActivityStream UI
    // - this integrates itself into the existing implementation, invoking dojo functionality internally
    renderTagsFilterComponent : async () => {
        // remove existing component
        let _customTagsFilterWrapper = document.querySelector('#custom_stream_tags_filter');
        if (_customTagsFilterWrapper) {
            _customTagsFilterWrapper.parentElement.removeChild(_customTagsFilterWrapper);
        }

        if (window.location.hash.indexOf('/updates/tags') < 0) {
            // only render on /updates/tags page
            return;
        }
        _customTagsFilterWrapper = document.createElement('div');
        _customTagsFilterWrapper.id = 'custom_stream_tags_filter';

        const _foundFilterArea = await window.ui._dom_element_is_available('.filterAreaInner .lotusRight');
        if (!_foundFilterArea) {
            return;
        }
        const _filterArea = document.querySelector('.filterAreaInner .lotusRight');
        _filterArea.prepend(_customTagsFilterWrapper);

        // gather existing filter options
        const _foundFilter = await window.ui._dom_element_is_available('.filterAreaInner .asFilterMenu select');
        if (!_foundFilter) {
            return;
        }
        const _filterList = document.querySelectorAll('.filterAreaInner .asFilterMenu select');
        if (_filterList.length < 1) {
            // expecting second filter to be available
            return;
        }
        const _filter = _filterList[_filterList.length - 1];
        let _options = [];
        const _selectedIdx = _filter.selectedIndex;
        for (let i=0; i< _filter.options.length; i++) {
            _options.push(_filter.options[i].text);
        };

        const _handleTagsFilterChange = (selectedIdx) => {
            // fetch underlying activity stream filter menu again to get up to date event handles (as this has been rerendered by dojo)
            const _filterList = document.querySelectorAll('.filterAreaInner .asFilterMenu select');
            if (_filterList.length < 1) {
                // expecting second filter to be available
                return;
            }
            const _filter = _filterList[_filterList.length - 1];
            const evt = new Event('change');
            _filter.selectedIndex = selectedIdx;
            _filter.dispatchEvent(evt);
        }

        const _handleManageTags = () => {
            const _manageTagsBtn = document.querySelector('.filterAreaInner .lotusRight .placeholderHeaderRightSub a');
            if (_manageTagsBtn) {
                _manageTagsBtn.click();
            }
        }

        // wait for loadReactApp to become available
        const _isReactAvailable = await window.ui._is_react_available('com.ibm.ics.apps.loadReactApp');
        if (_isReactAvailable) {
            com.ibm.ics.apps.loadReactApp(
                'cnx-react-components',
                'tags-filter',
                _customTagsFilterWrapper.id,
                Object.assign({
                    options: _options,
                    selectedOption: _selectedIdx,
                    handleChange: _handleTagsFilterChange,
                    handleManageTags: _handleManageTags
                }),
            );
        }

        // add update logic to manage tags actions
        const _foundSubmitButton = await window.ui._dom_element_is_available('.tagManagerDialog .lotusDialogFooter .lotusFormButton.submit');
        if (!_foundSubmitButton) {
            return;
        }
        let _submitButton = document.querySelector('.tagManagerDialog .lotusDialogFooter .lotusFormButton.submit');
        if (_submitButton.classList.contains('cnx8')) {
            // function already added, skip
            return;
        }
        _submitButton.classList.add('cnx8');
        _submitButton.addEventListener('click', function(e) {
            // rerender tags to apply any required updates
            com.hcl.connections.homepage.v8.renderTagsFilterComponent();
        });

        const _foundCloseButton = await window.ui._dom_element_is_available('.tagManagerDialog .lotusDialogHeader .lotusDialogClose');
        if (!_foundCloseButton) {
            return;
        }
        let _closeButton = document.querySelector('.tagManagerDialog .lotusDialogHeader .lotusDialogClose');
        if (_closeButton.classList.contains('cnx8')) {
            // function already added, skip
            return;
        }
        _closeButton.classList.add('cnx8');
        _closeButton.addEventListener('click', function(e) {
            // rerender tags to apply any required updates
            com.hcl.connections.homepage.v8.renderTagsFilterComponent();
        });
    },

    // remove existing personal filter component
    removePersonalFilterComponent : () => {
        const _customPersonalFilterWrapper = document.querySelector('#custom_stream_personal_filter');
        if (_customPersonalFilterWrapper) {
            _customPersonalFilterWrapper.parentElement.removeChild(_customPersonalFilterWrapper);
        }
    },

    // function to render the cnx-react-components/personal-filter component into the ActivityStream UI
    // - this integrates itself into the existing implementation, invoking dojo functionality internally
    renderPersonalFilterComponent : async () => {
        let _customPersonalFilterWrapper = document.createElement('div');
        _customPersonalFilterWrapper.id = 'custom_stream_personal_filter';

        const _foundFilterArea = await window.ui._dom_element_is_available('.filterAreaInner');
        if (!_foundFilterArea) {
            return;
        }
        const _filterArea = document.querySelector('.filterAreaInner');
        _filterArea.prepend(_customPersonalFilterWrapper);

        // gather existing filter options
        const _foundPersonalFilters = await window.ui._dom_element_is_available('.lotusWidgetBody#activityStreamMain .streamHeaderInner .lotusPaging .lotusLeft ul');
        if (!_foundPersonalFilters) {
            return;
        }

        const _filtersList = document.querySelector('.lotusWidgetBody#activityStreamMain .streamHeaderInner .lotusPaging .lotusLeft ul');
        if (!_filtersList || !_filtersList.children || !_filtersList.children.length) {
            return;
        }
        // make sure that the required config object is available before evaluating the filter content
        const _foundCnxConfig = await window.ui._js_object_is_available('activityStreamConfig.connections');
        if (!_foundCnxConfig) {
            return;
        }

        const _filters = _filtersList.children;
        let _options = [];
        let _selectedIdx = -1;
        for (let i=0; i< _filters.length; i++) {
            const _filter = _filters[i].querySelector('a');
            let _option = {
                label: _filter.textContent,
                notificationCount: 0,
                notificationReadOnOpen: false
            }

            // gather unread counts depending on the categories
            if (_filter.id === 'myNotifications') {
                _option.notificationCount = parseInt(activityStreamConfig.connections.unreadNotifications) || 0;
                _option.notificationReadOnOpen = true;

            } else if (_filter.id === 'atMentions') {
                _option.notificationCount = parseInt(activityStreamConfig.connections.unreadMentions) || 0;
                _option.notificationReadOnOpen = true;

            } else if (_filter.id === 'actionRequired' && lconn && lconn.homepage && lconn.homepage.global && lconn.homepage.global.actionRequiredTotal) {
                _option.notificationCount = parseInt(lconn.homepage.global.actionRequiredTotal) || 0;

            }
            _options.push(_option);

            // query for link with class "filterSelected" to determine which one is currently selected
            const _hasSelectedNode = _filters[i].querySelector('.filterSelected');
            if (_hasSelectedNode) {
                _selectedIdx = i;
            }
        };

        const _handlePersonalFilterChange = (selectedIdx) => {
            // fetch underlying activity stream filter menu again to get up to date event handles (as this has been rerendered by dojo)
            const _filtersList = document.querySelector('.lotusWidgetBody#activityStreamMain .streamHeaderInner .lotusPaging .lotusLeft ul');
            if (!_filtersList || !_filtersList.children || !_filtersList.children.length) {
                return;
            }
            if (_filtersList.children.length - 1 < selectedIdx) {
                com.hcl.connections.homepage.v8.log('renderPersonalFilterComponent:handlePersonalFilterChange - index out of bounds');
                return;
            }
            
            const _link = _filtersList.children[selectedIdx].querySelector('a');
            if (_link.id === 'atMentions') {
                dojo.publish(com.ibm.social.as.constants.events.BADGE_RESET, ['@mentions']);
            } else if (_link.id === 'myNotifications') {
                dojo.publish(com.ibm.social.as.constants.events.BADGE_RESET, ['@responses']);
            }

            _link.click();
            com.hcl.connections.homepage.v8.renderContentFilterComponent();
        };

        // wait for loadReactApp to become available
        const _isReactAvailable = await window.ui._is_react_available('com.ibm.ics.apps.loadReactApp');
        if (_isReactAvailable) {
            com.ibm.ics.apps.loadReactApp(
                'cnx-react-components',
                'personal-filter',
                _customPersonalFilterWrapper.id,
                Object.assign({
                    options: _options,
                    selectedOption: _selectedIdx,
                    handleChange: _handlePersonalFilterChange
                }),
            );
        }
    },

    // This is a function of the possible values that are supported as Feature Flags within Connections
    // Using gatekeeper within the application for feature flagging
    setOMGKConfig : () => {
        let omGKScript = document.createElement('script');
        const inlineScript = document.createTextNode(`window.omGKConfig = 
            {
            has: function (flag) {
                var enabledFlags = {"announcements-bar":true,"appreg-extensions-itm":true,"launch-ee-action":true,"cloud_navbar_homepage_switcher":true,"appreg-extensions":true,"om-linkify-hashtags":true,"userprefs":true};
                return enabledFlags[flag];
            }
            }
        ;`);
        omGKScript.appendChild(inlineScript);
        document.head.appendChild(omGKScript);
    },

    // function to render the cnx-react-components/content-filter component into the ActivityStream UI
    // - this integrates itself into the existing implementation, invoking dojo functionality internally
    renderTopUpdatesComponent : async () => {
        // wait for loadReactApp to become available
        const _isReactAvailable = await window.ui._is_react_available('com.ibm.ics.apps.loadReactApp');
        if (_isReactAvailable) {
            com.ibm.ics.loadBundles(() => {
                com.ibm.ics.apps.loadReactApp(
                    'orient',
                    'top-updates',
                    'top-updates',
                    { isOnlyTopUpdates: true }
                );
            });
        }
    },

    // identify and return the current Homepage view, differentiating between 'admin', 'myPage', 'topUpdates', 'discover' and 'myStream'
    getCurrentView : () => {
        const _currentPath = window.location.pathname;
        if (_currentPath.indexOf('/admin/') >= 0) {
            return '_admin';
        } else if (_currentPath.indexOf('/widgets/') >= 0) {
            return '_myPage';
        } else if (_currentPath.indexOf('/updates/') >= 0) {
            const _currentHash = window.location.hash;
            if (_currentHash.indexOf('#topUpdates/') >= 0) {
                return '_topUpdates';
            } else if (_currentHash.indexOf('#discover/') >= 0) {
                return '_discover';
            } else  if (_currentHash.indexOf('#myStream/') >= 0 || _currentHash.indexOf('#imFollowing/') >= 0) {
                return '_myStream';
            }
        }
        return null;
    },

    // generic updates to any homepage view
    adjustHomepageView : async () => {
        // wait for lotus content to become available
        // - remove titlebar (containing e.g. Profile image)
        const foundLotusContent = await window.ui._dom_element_is_available('.lotusContent');
        if (foundLotusContent) {
            let _titleBar2 = document.querySelector('#lotusFrame .lotusTitleBar2');
            if (_titleBar2) _titleBar2.remove();
        }
    },

    postAdjustHomepageView : async () => {
        const foundLotusFrame = await window.ui._dom_element_is_available('#lotusFrame');
        if (!foundLotusFrame) {
            return;
        }
        let _lotusFrame = document.querySelector('#lotusFrame');
        if (_lotusFrame) {
            _lotusFrame.style.display = 'block';
        }

        setTimeout(async () => {
            const foundLotusMain = await window.ui._dom_element_is_available('#lotusMain #lotusContent');
            const foundLotusFooter = await window.ui._dom_element_is_available('#lotusFooter li a');
            if (!foundLotusMain || !foundLotusFooter) {
                return;
            }
    
            let _lotusMain = document.querySelector('#lotusMain');
            let _lotusFooter = document.querySelector('#lotusFooter');
    
            let additionalHeight = _lotusFrame.offsetHeight - _lotusMain.offsetHeight - _lotusFooter.offsetHeight - 120;
            if (additionalHeight > 0) {
                _lotusMain.style.minHeight = `${_lotusMain.offsetHeight + additionalHeight}px`;
            }
        }, 10)
    },

    // adjust rich text editor to not affect certain elements, e.g. top updates component
    adjustRTE : async () => {
        // wait for CKEditor to become available
        const _isCKEditorAvailable = await window.ui._js_object_is_available('CKEDITOR');
        if (_isCKEditorAvailable) {

            // check whether a provided child dom element is contained within a parent
            // to identify whether RTE logic should be intercepted
            const _isDescendant = (parent, child) => {
                let node = child.parentNode;
                while (node != null) {
                    if (node == parent) {
                        return true;
                    }
                    node = node.parentNode;
                }
                return false;
            };

            // redirect any attempt to add a rich text editor to a hidden element so that it doesn't influence the actual elements we want
            let _dummyElement = document.getElementById('rte-redirection-node');
            if (!_dummyElement) {
                _dummyElement = document.createElement('br');
                _dummyElement.style.display = 'none';
                _dummyElement.id = 'rte-redirection-node';
                document.body.appendChild(_dummyElement);
            }

            CKEDITOR.add = function() {
                const _topUpdatesParent = document.getElementById('top-updates');
                if (_topUpdatesParent) {
                    const _targetElement = arguments[0].element.$;
                    if (_isDescendant(_topUpdatesParent, _targetElement)) {
                        arguments[0].element.$ = _dummyElement;
                    }
                }
            }
        }
    },

    // generic updates to the activity stream page
    adjustActivityStreamView : async () => {
        // overload rich text editor to not conflict with top updates view
        com.hcl.connections.homepage.v8.adjustRTE();

        // add the window.features object required for some communication in the ome/top updates component
        if (!window.features) {
            const _scheme = window.location.protocol === 'https:' ? 'https' : 'http';

            window.features = {
                icConfigDeployment: 'CNX',
                icDeploymentType: 'HYBRID_CLOUD',
                icDeploymentHost: window.location.hostname,
                icDeploymentScheme: _scheme,
                icUseRelPath: false,
            }
        }

        // move customize button to widget section
        const _customizerLinkFound = await window.ui._dom_element_is_available('a#paletteLink');
        const _lotusColRightFound = await window.ui._dom_element_is_available('#lotusColRight');
        if (_customizerLinkFound && _lotusColRightFound) {
            let _widgetDiv = document.querySelector('#lotusColRight');
            com.hcl.connections.homepage.v8.updateWidgetCustomizerBtn(_widgetDiv, false);
    
            let _lotusTitleBarExt = document.querySelector('.lotusTitleBarExt');
            if (_lotusTitleBarExt) {
                _lotusTitleBarExt.parentElement.removeChild(_lotusTitleBarExt);
            }
        }
    },

    // - select top updates navigation point
    // - hide the original activity stream
    // - render the top updates react component
    renderTopUpdatesView : async () => {
        // add lotusSelected to top updates navigation element
        const _foundTopUpdatesNavLink = await window.ui._dom_element_is_available('li.lconnHomepage-topUpdates');
        if (!_foundTopUpdatesNavLink) {
            return;
        }
        let _topUpdatesNavLink = document.querySelector('li.lconnHomepage-topUpdates');
        if (_topUpdatesNavLink) {
            _topUpdatesNavLink.classList.add('lotusSelected');
        }

        const foundActivityStreamWrapper = await window.ui._dom_element_is_available('.activityStreamPage.lotusMain');
        if (!foundActivityStreamWrapper) {
            return;
        }
        let _activityStreamWrapper = document.querySelector('.activityStreamPage.lotusMain');
        _activityStreamWrapper.classList.add('lotusHidden');

        // add top updates wrapper before activity stream wrapper, hide as wrapper
        let top_updates = document.createElement('div');
        top_updates.id = 'top-updates';
        top_updates.classList.add('lotusContent');
        top_updates.classList.add('lotusBoard');
        top_updates.classList.add('ic-top-updates');
        _activityStreamWrapper.parentElement.insertBefore(top_updates, _activityStreamWrapper);

        com.hcl.connections.homepage.v8.setOMGKConfig();

        // CNXSERV-17533 - remove existing .filterbarTypeaheadContainer
        let _filterbarTypeaheadContainers = document.querySelectorAll('.filterbarTypeaheadContainer');
        if(_filterbarTypeaheadContainers && _filterbarTypeaheadContainers.length) {
            _filterbarTypeaheadContainers.forEach(entry => entry.remove());
        }

        com.hcl.connections.homepage.v8.renderTopUpdatesComponent();
    },

    // - select navigation point of selected AS view (latest updates or discover)
    // - remove some areas from filter section, e.g. description text
    // - remove the top updates component in case it is currently added to the content
    // - resize sharebox and center it in view
    // - render the react content filter component, overlaying the original filter selection
    renderActivityStreamView : async (_currentView) => {
        // add lotusSelected to selected nav link
        const _foundNavElement = await window.ui._dom_element_is_available('#' + _currentView);
        if (!_foundNavElement) {
            return;
        }
        const _navElement = document.querySelector('#' + _currentView);
        if (_navElement && _navElement.parentElement) {
            _navElement.parentElement.classList.add('lotusSelected');
        }

        // Remove top updates component and unhide ActivityStream content area
        let topUpdatesElem = document.getElementById('top-updates');
        if (topUpdatesElem) {
            topUpdatesElem.remove();

            let _activityStreamWrapper = document.querySelector('.activityStreamPage.lotusMain');
            if (_activityStreamWrapper)
                _activityStreamWrapper.classList.remove('lotusHidden');
        }
        
        // wait for filter area to become available
        // - remove description of AS type
        const foundFilterArea = await window.ui._dom_element_is_available('.filterArea');
        if (foundFilterArea) {
            let _asDesc = document.querySelector('#asDesc');
            if (_asDesc) _asDesc.remove();
        }

        com.hcl.connections.homepage.v8.removePersonalFilterComponent();
        if (_currentView === '_myStream') {
            com.hcl.connections.homepage.v8.renderPersonalFilterComponent();
        }
        com.hcl.connections.homepage.v8.renderContentFilterComponent();

        // wait for sharebox to become available
        // - add resize logic and onresize listener to center sharebox and activity stream header
        let _streamShareBox = document.querySelector('.streamSharebox');
        let _streamHeaderInner = document.querySelector('.streamHeaderInner');
        let _activityStreamHeader = document.querySelector('#activityStreamHeader');
        let _activityStreamDiv = document.querySelector('#activityStream');

        /* CNXSERV-16788 - change resize method to take into consideration absolute positioning updates */
        const _resizeStreamHeader = () => {
            _activityStreamDiv = document.querySelector('#activityStream');
            _streamHeaderInner = document.querySelector('.streamHeaderInner');

            let _streamWidth = _activityStreamDiv.offsetWidth;
            let _shareBoxWidth = _streamHeaderInner.offsetWidth;

            let _leftPadding = Math.floor((_streamWidth - _shareBoxWidth) / 2);
            _streamHeaderInner.style.left = `${_leftPadding}px`;
            _activityStreamHeader.style.left = `${_leftPadding}px`;
        }
    
        let _resizeTimeout = false;
        const _lotusFrame = document.getElementById('lotusFrame');
        new ResizeObserver(_resizeStreamHeader).observe(_lotusFrame);
        window.addEventListener('resize', () => {
            clearTimeout(_resizeTimeout);
            _resizeTimeout = this.setTimeout(_resizeStreamHeader, 100);
        });
        _resizeStreamHeader();
        if (!_streamShareBox || !_streamHeaderInner || !_activityStreamHeader || _streamShareBox.offsetWidth <= 0) {
            // call again to ensure resize is invoked
            const foundShareBox = await window.ui._dom_element_is_available('.lconnShareboxCke');
            if (foundShareBox) {
                _resizeStreamHeader();
            }
        }
    },
    
    // - select navigation point of selected AS view (My Page)
    // - fetch customize button and set margin
    // - add wheel icon before customizer button and added onClick listener
    // - create new div and add setting/wheel icon and customizer button inside this div
    renderMyPageView : async (_currentView) => {
    	// add lotusSelected to my page navigation element
        let _myPageNavLink = document.querySelector('li.lconnHomepageMyPage');
        if (_myPageNavLink) {
            _myPageNavLink.classList.add('lotusSelected');
        }     
        
        //add wheel/setting icon before customizer button and added onClick listener
        const _beforePaletteLinkFound = await window.ui._dom_element_is_available('a#beforePaletteLink');
        if (_beforePaletteLinkFound) {
            let _beforePaletteLink = document.querySelector('a#beforePaletteLink');  	         
            _beforePaletteLink.innerHTML =
            '<svg width="18" height="18" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="cog" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path fill="#01539B" d="M487.4 315.7l-42.6-24.6c4.3-23.2 4.3-47 0-70.2l42.6-24.6c4.9-2.8 7.1-8.6 5.5-14-11.1-35.6-30-67.8-54.7-94.6-3.8-4.1-10-5.1-14.8-2.3L380.8 110c-17.9-15.4-38.5-27.3-60.8-35.1V25.8c0-5.6-3.9-10.5-9.4-11.7-36.7-8.2-74.3-7.8-109.2 0-5.5 1.2-9.4 6.1-9.4 11.7V75c-22.2 7.9-42.8 19.8-60.8 35.1L88.7 85.5c-4.9-2.8-11-1.9-14.8 2.3-24.7 26.7-43.6 58.9-54.7 94.6-1.7 5.4.6 11.2 5.5 14L67.3 221c-4.3 23.2-4.3 47 0 70.2l-42.6 24.6c-4.9 2.8-7.1 8.6-5.5 14 11.1 35.6 30 67.8 54.7 94.6 3.8 4.1 10 5.1 14.8 2.3l42.6-24.6c17.9 15.4 38.5 27.3 60.8 35.1v49.2c0 5.6 3.9 10.5 9.4 11.7 36.7 8.2 74.3 7.8 109.2 0 5.5-1.2 9.4-6.1 9.4-11.7v-49.2c22.2-7.9 42.8-19.8 60.8-35.1l42.6 24.6c4.9 2.8 11 1.9 14.8-2.3 24.7-26.7 43.6-58.9 54.7-94.6 1.5-5.5-.7-11.3-5.6-14.1zM256 336c-44.1 0-80-35.9-80-80s35.9-80 80-80 80 35.9 80 80-35.9 80-80 80z" class=""></path></svg>';
            
        }
        
        // create new div and add setting/wheel icon and customizer button inside this div
        const _customizerLinkFound = await window.ui._dom_element_is_available('a#paletteLink');
        if (_customizerLinkFound) {
            let _widgetDiv = document.querySelector('#lotusContent');
            com.hcl.connections.homepage.v8.updateWidgetCustomizerBtn(_widgetDiv, true);
    
            let _lotusTitleBarExt = document.querySelector('.lotusTitleBarExt');
            if (_lotusTitleBarExt) {
                _lotusTitleBarExt.parentElement.removeChild(_lotusTitleBarExt);
            }
        }
    },

    // update styles for the customizer elements, add it to the right position
    updateWidgetCustomizerBtn : (customizerWrapper, isMyPage) => {
        if (!customizerWrapper) {
            return;
        }

        let _paletteLink = document.querySelector("a#paletteLink");
        let _beforePaletteLink = document.querySelector('a#beforePaletteLink'); 

        // create wrapper element
        let _customizerDiv = document.createElement('div');
        _customizerDiv.id = 'widgetCustomizer';
        _customizerDiv.className = isMyPage ? 'myPageCustomizer' : 'asCustomizer';

        // put onclick listener on wrapper
        _paletteLink.removeAttribute("onclick");
        _customizerDiv.addEventListener("click", function () {
            lconn.homepage.global.openPalette();
            com.hcl.connections.homepage.v8.adjustCustomizeMyPagePalette();
        });

        if (!_beforePaletteLink) {
            _beforePaletteLink = document.createElement('a');
        }
        _beforePaletteLink.id = 'beforePaletteLink';
        _beforePaletteLink.innerHTML =
        '<svg width="18" height="18" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="cog" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path fill="#01539B" d="M487.4 315.7l-42.6-24.6c4.3-23.2 4.3-47 0-70.2l42.6-24.6c4.9-2.8 7.1-8.6 5.5-14-11.1-35.6-30-67.8-54.7-94.6-3.8-4.1-10-5.1-14.8-2.3L380.8 110c-17.9-15.4-38.5-27.3-60.8-35.1V25.8c0-5.6-3.9-10.5-9.4-11.7-36.7-8.2-74.3-7.8-109.2 0-5.5 1.2-9.4 6.1-9.4 11.7V75c-22.2 7.9-42.8 19.8-60.8 35.1L88.7 85.5c-4.9-2.8-11-1.9-14.8 2.3-24.7 26.7-43.6 58.9-54.7 94.6-1.7 5.4.6 11.2 5.5 14L67.3 221c-4.3 23.2-4.3 47 0 70.2l-42.6 24.6c-4.9 2.8-7.1 8.6-5.5 14 11.1 35.6 30 67.8 54.7 94.6 3.8 4.1 10 5.1 14.8 2.3l42.6-24.6c17.9 15.4 38.5 27.3 60.8 35.1v49.2c0 5.6 3.9 10.5 9.4 11.7 36.7 8.2 74.3 7.8 109.2 0 5.5-1.2 9.4-6.1 9.4-11.7v-49.2c22.2-7.9 42.8-19.8 60.8-35.1l42.6 24.6c4.9 2.8 11 1.9 14.8-2.3 24.7-26.7 43.6-58.9 54.7-94.6 1.5-5.5-.7-11.3-5.6-14.1zM256 336c-44.1 0-80-35.9-80-80s35.9-80 80-80 80 35.9 80 80-35.9 80-80 80z" class=""></path></svg>';

        _customizerDiv.appendChild(_beforePaletteLink);
        _customizerDiv.appendChild(_paletteLink);
        customizerWrapper.prepend(_customizerDiv);

        com.hcl.connections.homepage.v8.sanitizeCustomizerClickBehavior();
    },

    /*CNXSERV-14484 - Customize my page palette*/
    adjustCustomizeMyPagePalette : async() => {
        const foundPaletteWrapper = await window.ui._dom_element_is_available('#palette_modal_id #paletteWrapper .app_palette_modal');
        if(foundPaletteWrapper){
            let browseLabelNode = document.querySelector('#palette_modal_id #paletteWrapper .app_palette_modal .lotusPaletteBody .lotusPaletteNav h3');
            let paletteNav = document.querySelector('#palette_modal_id #paletteWrapper .app_palette_modal .lotusPaletteBody .lotusPaletteNav');
            paletteNav.removeChild(browseLabelNode);
        }
    },

    sanitizeCustomizerClickBehavior: () => {
        // ensure customize modal always appears centered
        let _widgetCustomizer = document.querySelector('#widgetCustomizer');
        if (_widgetCustomizer) {
            _widgetCustomizer.addEventListener('click', () => {

                const centerMyPageCustomizer = () => {
                    let _x = Math.floor((window.innerWidth - 590) / 2);
                    let _y = Math.floor((window.innerHeight - 720) / 2);

                    let _customizerModal = document.querySelector('#palette_modal_id');
                    if (_customizerModal) {
                        _x = Math.floor((window.innerWidth - _customizerModal.offsetWidth) / 2);
                        _y = Math.floor((window.innerHeight - _customizerModal.offsetHeight) / 2);

                        if (_y < 15) {
                            _y = 15;
                            // set height to 50px smaller than window viewport to size it correctly
                            let modalHeight = window.innerHeight - 50;
                            _customizerModal.style.height = `${modalHeight}px`;
                            _customizerModal.style.overflow = 'auto';
                        }
                        _customizerModal.style.left = `${_x}px`;
                        _customizerModal.style.top = `${_y}px`;
                    }
                }

                let _intervalCount = 0;
                const _intervalChange = setInterval(() => {
                    if (++_intervalCount > 4) {
                        clearInterval(_intervalChange);
                        return;
                    }
                    centerMyPageCustomizer();
                }, 250);
                centerMyPageCustomizer();
            });
        }
    },

    sanitizeRequestUri: () => {
        let _href = window.location.href;
        // https://jira.cwp.pnp-hcl.com/browse/CNXSERV-11560 
        if (_href.indexOf('/web/updates/#myNotifications/') > 0) {
            _href = _href.replace('/web/updates/#myNotifications/', '/web/updates/#myStream/myNotifications/');
            window.location.href = _href;
        }
    },

    focusOnFilterTopUpdates: async () => {
        const foundLotusMain = await window.ui._dom_element_is_available('#ic-web-filterbar .placeholderCNX8Class');
        if (foundLotusMain) {
            document.querySelector('#ic-web-filterbar .placeholderCNX8Class').click();
        }
    },

    handleOnShowURIPreview : async (e) => {
        const _foundLconnPreview = await window.ui._dom_element_is_available('div.lconnPreview');

        const lotusColRight =  document.querySelector('.lotusColRight');
        const activityMainIsExpanded =  document.querySelector('div.isExpanded');

        if (!activityMainIsExpanded || !lotusColRight) {
            return;
        }

        if(_foundLconnPreview && e.detail ){
            lotusColRight.classList.add('uriPreviewShown');
            activityMainIsExpanded.classList.add('uriPreviewShown');
        }
        if(!e.detail){
            lotusColRight.classList.remove('uriPreviewShown');
            activityMainIsExpanded.classList.remove('uriPreviewShown');
        }
    },

    updateCnx8HomepageNav : async () => {
        // add lotusSelected navigation highlighting logic to new nav
        const _foundTertiaryLevelNav = await window.ui._dom_element_is_available('#tertiary_level_nav li');
        if (_foundTertiaryLevelNav) {

            let _navEntries = document.querySelectorAll('#tertiary_level_nav li');
            for (let i=0; i<_navEntries.length; i++) {
                _navEntries[i].addEventListener('click', function(e) {
                    let _currentView = null;
                    let _targetEl = e.target;
                    if (_targetEl.id !== '') {
                        _currentView = _targetEl.id;
                    } else if (_targetEl.href !== null) {
                        const _targetLink = _targetEl.href;
                        if (_targetLink.indexOf('/admin/') >= 0) {
                            _currentView = '_admin';
                        } else if (_targetLink.indexOf('/widgets/') >= 0) {
                            _currentView = '_myPage';
                        }
                    } else {
                        _currentView = com.hcl.connections.homepage.v8.getCurrentView();
                    }

                    if (['_admin', '_myPage'].includes(_currentView)) {
                        // don't update ui again - this may lead to ui flickers if jumping from AS to myPage
                        return;
                    }
                    com.hcl.connections.homepage.v8.updateCnx8HomepageUi(_currentView);

                    const _navEntryId = e.target.id;
                    // toggle onclick of original link
                    if (!_navEntryId || _navEntryId.length <= 0) {
                        return;
                    }
                    const _originalElement = document.querySelector('#homepageLeftNavigationMenuContainer #' + _navEntryId);
                    if (_originalElement) {
                        e.preventDefault();
                        e.stopPropagation();

                        _originalElement.click();
                    }
                })
            }
        }
    },

    /* 
     * This code adjusts and extends the visible UI depending on the particular view in the following ways:
     * 
     * In general:
     *      This code ensures that the link that has been selected is highlighted (lotusSelected), and the underlying onClick events are triggered.
     * 
     * Top Updates:
     *      This is implemented as an additional "Activity Stream view" to take advantage of some baseline functionality. Additionally, it alters the UI in the following way:
     *      - Hiding the general AS content (stream, widget area) and adds a new content wrapper for the top updates component.
     *      - Removing some unnecessary content (e.g. profile image)
     *      - Rendering the Top Updates react component
     * 
     * Latest Updates / Discover page
     *      Here the code overlays the original Activity Stream UI and applies various changes:
     *      - Removing some unnecessary content (e.g. profile image)
     *      - Ensuring any changes done for Top Updates are reverted (e.g. Activity Stream wrapper is showing again, Top Updates component is removed)
     *      - Placing the Sharebox at the top center of the page, and adjusting positioning on viewport change to ensure it remains centered
     *      - Rendering the Content Filter react component, which pipes onchange events to the original underlying implementation in order to retain dojo functionality
     *      
     * My Page:    
     *      Here the code overlays the original My Page UI and applies various changes:
     *      - Removing some unnecessary content (e.g. profile image)
     *      - Adjust margin for customize link and add wheel icon with it
     * 		- Fetch customize button and set margin
     *		- Add wheel icon before customizer button and added onClick listener
     *		- Create new div and add setting/wheel icon and customizer button inside this div
     */
    updateCnx8HomepageUi : async (_currentView) => {

        // remove lotusSelected from prior element
        const _navEntriesSelected = document.querySelectorAll('#tertiary_level_nav li.lotusSelected');
        for (let i=0; i<_navEntriesSelected.length; i++) {
            _navEntriesSelected[0].classList.remove('lotusSelected');
        }
        com.hcl.connections.homepage.v8.adjustHomepageView();

        if (_currentView === '_topUpdates') {
            com.hcl.connections.homepage.v8.adjustActivityStreamView();
            com.hcl.connections.homepage.v8.renderTopUpdatesView();
            document.addEventListener('onFilterFocused', event => {
                com.hcl.connections.homepage.v8.focusOnFilterTopUpdates();
            });
        } else if (_currentView === '_discover' || _currentView === '_myStream') {
            com.hcl.connections.homepage.v8.adjustActivityStreamView();
            com.hcl.connections.homepage.v8.renderActivityStreamView(_currentView); 

        } else if (_currentView === '_myPage') {
        	com.hcl.connections.homepage.v8.renderMyPageView(_currentView);
        	
        } else if (_currentView === '_admin') {
            // add lotusSelected to my page navigation element
            const _adminNavLink = document.querySelector('li.lconnHomepageAdmin');
            if (_adminNavLink) {
                _adminNavLink.classList.add('lotusSelected');
            }
        }

        com.hcl.connections.homepage.v8.postAdjustHomepageView();
    },

    addOnChangeListeners : () => {
        /* CNXSERV-17136 this code catches URI Preview changes */
        window.addEventListener('lconn.homepage.onShowURIPreview', com.hcl.connections.homepage.v8.handleOnShowURIPreview);
    }
};

(() => {
    // if customizations have been provided via JS object 'com.hcl.connections.customization.homepage.v8', overwrite respective functions in here
    if (com.hcl.connections.customization && com.hcl.connections.customization.homepage && com.hcl.connections.customization.homepage.v8) {
        // create a copy of the original functions so customization can call them
        com.hcl.connections.homepage.v8_default = Object.assign(com.hcl.connections.homepage.v8);
        Object.assign(com.hcl.connections.homepage.v8, com.hcl.connections.customization.homepage.v8);
    }

    // correct url in case it would not work in cnx8 anymore
    com.hcl.connections.homepage.v8.sanitizeRequestUri();

    // render/extend the homepage view that should currently be displayed
    const _currentView = com.hcl.connections.homepage.v8.getCurrentView();
    com.hcl.connections.homepage.v8.updateCnx8HomepageUi(_currentView);
    // add additional event handling to tertiary nav bar
    com.hcl.connections.homepage.v8.updateCnx8HomepageNav();
    //CNXSERV-17136 add listeners
    com.hcl.connections.homepage.v8.addOnChangeListeners();
})();
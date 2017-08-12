
(function ($) {

    var hash = window.location.hash;

    $(function () {
        window.mainpage.instMainMenus();
        window.mainpage.bindNavTabsButtonEvent();
        window.mainpage.bindToolbarButtonEvent();
        window.mainpage.bindMainTabsButtonEvent();

        var portal = $("#portal"), layout = $("#mainLayout"), navTabs = $("#navTabs"), themeCombo = $("#themeSelector");

        $.util.exec(function () {
            var theme = $.easyui.theme(), themeName = $.cookie("themeName");
            if (themeName && themeName != theme) { window.mainpage.setTheme(themeName, false); }

            layout.removeClass("hidden").layout("resize");

            $("#maskContainer").remove();

            var size = $.util.windowSize();
            //  判断当浏览器窗口宽度小于像素 1280 时，右侧 region-panel 自动收缩
            if (size.width < 1280) { layout.layout("collapse", "east"); }

            window.mainpage.mainTabs.loadHash(hash);
        });
    });

})(jQuery);
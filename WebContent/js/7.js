"use strict";
var winW;
var winH;
var es_step = "Expo.ease";
var $window = $(window);
var winSc;
var $container = $("#container");
var $subVisual = $container.find("#subVisual");
var isRunning = true;

$window.load(function () {
    winSc = $(this).scrollTop();
    $window.on("resize", function () {
        winW = $(this).width();
        winH = $(this).height();
    });
    $(this).trigger("resize");

    $(window).scroll(function () {
        winSc = $(this).scrollTop();
    });
    layout();
    main();
    scrollBg();
    locationJS();
    object();
    about();
    research();
});

function about() {
// press
    var $press = $(".press"),
        $listSelect = $press.find(".list_select");

    $listSelect.find("li").click(function () {
        var _index = $(this).index();
        $listSelect.find("li button").removeClass("active");
        $(this).find("button").addClass("active");
        if (_index === 1) {
            $(".board_list ul").addClass("list_shape");
        } else {
            $(".board_list ul").removeClass("list_shape");
        }
    });

//labs
    /*var $saitList = $(".sait_list");
    var $saitView = $(".sait_view");
    var $saitViewWrap = $saitView.find(".view_wrap");
    var $saitContent = $saitView.find("article");
    var labsMap = [
    //SRJ-Japan
    "https://www.google.com/maps/embed/v1/place?q=Minoh%20Semba%20Center%20Bldg.%2C%202-1-11%20Semba%20Nishi%2C%20Osaka%2C%20Japan&key=AIzaSyDd3NCdXT62bscYDaMS-y61srz-gpP-WKk,",
    //SAIT-America
    "https://www.google.com/maps/embed/v1/place?q=665%20Clyde%20Ave.%20Mountain%20View%2C%20CA%2094043&key=AIzaSyDd3NCdXT62bscYDaMS-y61srz-gpP-WKk",
    //SAIT-Russia
    "https://www.google.com/maps/embed/v1/place?q=place_id:ChIJoeX0W_9JtUYRkZn4itP2CuY&key=AIzaSyDd3NCdXT62bscYDaMS-y61srz-gpP-WKk",
    //SAIT-China
    "https://www.google.com/maps/embed/v1/place?q=Sun%20Palace%20Building%20Taiyanggong%20Mid%20Road%2C%20Chaoyang%20District%2C%20Beijing&key=AIzaSyDd3NCdXT62bscYDaMS-y61srz-gpP-WKk",
    //SAIT-Europe
    "https://www.google.com/maps/embed/v1/place?q=Samsung%20R%26D%20Institute%20UK%2C%20Communications%20House%2C%20South%20Street%2C%20%20Staines-upon-%20Thames%2C%20Surrey%2C%20TW18%204QE%2C%20UK.%2C%20Staines-Upon-Thames%2C%20%20United%20Kingdom&key=AIzaSyDd3NCdXT62bscYDaMS-y61srz-gpP-WKk",
    //SAIT-India
    "https://www.google.com/maps/embed/v1/place?q=Phoenix%20B'd%2C%20Bagmane%20Constellation%20business%20Park%20Bangalore%20Karnataka%2C%20India%2006765%20&key=AIzaSyDd3NCdXT62bscYDaMS-y61srz-gpP-WKk",
    //SAIT-Ukraine
    "https://www.google.com/maps/embed/v1/place?q=57%20L'va%20Tolstogo%20Street%2C%20Kiev%20Ukraine%2001032&key=AIzaSyDd3NCdXT62bscYDaMS-y61srz-gpP-WKk"
    ];
    $saitList.find("li").click(function () {
    var _this = $(this);
    var _index= _this.index();
    var _$saitContentHeight = $saitContent.eq(_index).height()-450;
    $saitList.find("li button").removeClass("active");
    _this.find("button").addClass("active");
    TweenMax.to($saitContent, .3, {opacity:0, y:50, display:"none", ease:es_step});
    TweenMax.to($saitContent.eq(_index), .3, {opacity:1, y:0, display:"block", ease:es_step});
    TweenMax.to($saitViewWrap, .3, {height:_$saitContentHeight});
    $("#googleMap").attr("src", labsMap[_index]);
    });*/

//faq
    var $faqWrap = $(".faq_wrap");
    var $questionBtn = $faqWrap.find("button");
    $questionBtn.click(function () {
        var $questionLi = $(this).parent();
        var $answer = $(this).siblings();
        var $answerTextH = $answer.find(".answer_txt").innerHeight();
        if (!$questionLi.hasClass("active")) {
            $questionLi.addClass("active");
            TweenMax.to($answer, .3, {height: $answerTextH, ease: es_step});
        } else {
            $questionLi.removeClass("active");
            TweenMax.to($answer, .3, {height: 0, ease: es_step});
        }
    });
}


function layout() {
    var $header = $("#header");
    var $gnb = $("#gnb");
    var $searchBtn = $("#searchBtn");
    var $gnbDepth1 = $gnb.find(".depth1");
    var $headerSearch = $("#headerSearch");
//gnb open/close
    $gnbDepth1.find("> li").on("mouseenter.depth01 focusin", function () {
        gnbOpen();
    });
    $gnb.find(".header_banner").focus(function () {
        gnbOpen();
    });
    $header.on("mouseleave.depth01 focusout", function () {
        gnbClose();
        event.preventDefault();
    });
    $searchBtn.on("keydown", function (e) {
        var tab = Tab(function () {
            gnbClose();
        }, e);
    });

    function gnbOpen() {
        $header.find(".header_wrap").addClass("header_open");
        TweenMax.to($header, .3, {height: 722, ease: es_step});
    }

    function gnbClose() {
        $header.find(".header_wrap").removeClass("header_open");
        TweenMax.to($header, .3, {height: 104, ease: es_step});
    }

//gnb_search
    $searchBtn.click(function () {
        var _this = $(this);
        if (!_this.hasClass("active")) {
            _this.addClass("active");
            TweenMax.to($headerSearch, .3, {height: 120, display: "block", ease: es_step});
            _this.text("search form close");
        } else {
            _this.removeClass("active");
            TweenMax.to($headerSearch, .3, {height: 0, display: "none", ease: es_step});
            _this.text("search form open");
        }
    });
}

function locationJS() {
    var $subLocation = $("#subLocation");
    var $locationBox = $(".location_box");
    $subLocation.find("button").on("mouseenter focusin", function () {
        event.preventDefault();
        var _this = $(this);
        var locationDepthH = _this.siblings(".under_depth").outerHeight();
        if (!_this.hasClass("active")) {
            _this.addClass("active");
            TweenMax.to($(".location_list > li"), .3, {height: 76, ease: es_step});
            TweenMax.to($(this).parent(), .3, {height: locationDepthH + 76, ease: es_step});
        } else {
            if (!TweenMax.isTweening($(".location_list > li"))) {
                $subLocation.find("button").removeClass("active");
                TweenMax.to($(this).parent(), .3, {height: 76, ease: es_step});
            }
        }
    });

    $(".location_list > li").on("mouseleave", function () {
        $subLocation.find("button").removeClass("active");
        TweenMax.to($(".location_list > li"), .3, {height: 76, ease: es_step});
    });

//占쎈쵌�먲옙占� 占쎈똻��
    var $underDepth = $subLocation.find(".depth1, .depth2");
    $underDepth.each(function () {
        var _this = $(this);
        _this.find("li").eq(-1).find("a").keydown(function (e) {
            if (!TweenMax.isTweening($(".location_list > li"))) {
                var tab = Tab(function () {
                    $subLocation.find("button").removeClass("active");
                    TweenMax.to($(".location_list > li"), .3, {height: 76, ease: es_step});
                }, e);
            }
        });
    });

//占쎌뮆�� 占쎈챸�껅에占� 占쎌눖釉� 嚥≪뮇占쏙옙�곷��
    $(window).scroll(function () {
        if (winSc > 513) {
            $locationBox.addClass("active");
        } else {
            $locationBox.removeClass("active");
        }
    });
}

function main() {
//mainVisual
    var $mainVisual = $("#mainVisual");
    var $visualWrap = $mainVisual.find(".visual_wrap"),
        $visualWrapLi = $visualWrap.find("li"),
        _visualLength = $visualWrapLi.length;

    var $navBtn = $mainVisual.find("#navBtn"),
        $prevBtn = $navBtn.find(".prev"),
        $nextBtn = $navBtn.find(".next");

    var $indiBtn = $mainVisual.find("#indiBtn");
    TweenMax.staggerTo($visualWrapLi.eq(0).find("h3,p,a"), .3, {y: 0, opacity: 1, ease: es_step}, .2);
    var visualCrr = {};
    
    Object.defineProperty(visualCrr, 'number', {
        get: function () {
            return this.num || 0;
        },
        set: function (_visualCrr) {
            _visualCrr = _visualCrr % _visualLength;
            TweenMax.to($visualWrapLi, .3, {opacity: 0, display: "none", ease: es_step});
            TweenMax.to($visualWrapLi.find("h3,p,a"), .5, {y: 30, opacity: 0, ease: es_step});
            TweenMax.to($visualWrapLi.eq(_visualCrr), .3, {opacity: 1, display: "block", ease: es_step});
            TweenMax.staggerTo($visualWrapLi.eq(_visualCrr).find("h3,p,a"), 1, {y: 0, opacity: 1, ease: es_step}, .2);

            $indiBtn.find("li button").removeClass("active");
            $indiBtn.find("li").eq(_visualCrr).find("button").addClass("active");
            this.num = _visualCrr;
        }
    });
    $nextBtn.click(function () {
        visualCrr.number++;
    });
    $prevBtn.click(function () {
        visualCrr.number--;
    });
    $indiBtn.find("button").click(function () {
        var _this = $(this);
        var _visualCrr = _this.parent().index();

        visualCrr.number = _visualCrr;
    });

     //재생, 멈춤 버튼 추가 
    var $startBtn = $navBtn.find(".start"),
    	$pauseBtn = $navBtn.find(".pause");
    
    $startBtn.click(function(){
       isRunning = true;
   });

    $pauseBtn.click(function(){
       isRunning = false; 
    });
	

//main Research
    var $mainResearch = $(".main_research"),
        $mainResearchUl = $mainResearch.find("ul"),
        _UlLength = $mainResearchUl.length;
    var $researchPrev = $mainResearch.find(".prev");
    var $researchNext = $mainResearch.find(".next");

    var researchCrr = 0;
    $researchNext.click(function () {
        if (!TweenMax.isTweening($mainResearchUl)) {
            researchCrr++;
            researchCrr = researchCrr % _UlLength;
            TweenMax.to($mainResearchUl, .3, {x: -400, opacity: 0, display: "none", ease: es_step});
            TweenMax.fromTo($mainResearchUl.eq(researchCrr), .3, {x: 400, opacity: 1}, {
                x: 0,
                opacity: 1,
                display: "block",
                ease: es_step
            });
        }
    });
    $researchPrev.click(function () {
        if (!TweenMax.isTweening($mainResearchUl)) {
            researchCrr--;
            researchCrr = researchCrr % _UlLength;
            TweenMax.to($mainResearchUl, .3, {x: 400, opacity: 0, display: "none", ease: es_step});
            TweenMax.fromTo($mainResearchUl.eq(researchCrr), .3, {x: -400, opacity: 1}, {
                x: 0,
                opacity: 1,
                display: "block",
                ease: es_step
            });
        }
    });

    TweenMax.staggerTo($mainResearch.find(".js-act-research"), .8, {
        y: 0, opacity: 1, ease: es_step
    }, .2);

//forum
    var $forumWrap = $(".forum_wrap");
    var $forumArticle = $forumWrap.find("article");

    $forumArticle.each(function () {
        var _this = $(this);
        var _conLength = _this.find("li").length;
        var forumCrr = 0;
        _this.find(".next").click(function () {
            forumCrr++;
            forumCrr = forumCrr % _conLength;

            TweenMax.to($(this).parent().siblings("ul").find("li"), .3, {opacity: 0, display: "none", ease: es_step});
            TweenMax.to($(this).parent().siblings("ul").find("li").eq(forumCrr), .3, {
                opacity: 1,
                display: "block",
                ease: es_step
            });
        });
        _this.find(".prev").click(function () {
            forumCrr--;
            forumCrr = forumCrr % _conLength;

            TweenMax.to($(this).parent().siblings("ul").find("li"), .3, {opacity: 0, display: "none", ease: es_step});
            TweenMax.to($(this).parent().siblings("ul").find("li").eq(forumCrr), .3, {
                opacity: 1,
                display: "block",
                ease: es_step
            });
        });
    });


    setInterval(function () {
       if(isRunning) visualCrr.number++;
    }, 5000);
}

function object() {
//custom select
    var $selCustom = $("#selectCustom"),
        $selBtn = $selCustom.find("> button");
    var $selWrap = $selCustom.find("ul"),
        $seltOption = $selWrap.find("button"),
        $seltOptionLength = $selWrap.find("li").size();

    $selBtn.click(function () {
        $(this).addClass("active");
        TweenMax.to($selWrap, .3, {display: "block", height: $seltOptionLength * 37 + 12, ease: es_step});
    });
    $seltOption.click(function () {
        $seltOption.removeClass("active");
        $(this).addClass("active");
        $selBtn.text($(this).text());
        seltOptionStep()
    });
    $selCustom.on("mouseleave", function () {
        seltOptionStep();
    });

    function seltOptionStep() {
        $selBtn.removeClass("active");
        TweenMax.to($selWrap, .3, {display: "none", height: 0, ease: es_step});
    }

    $seltOption.parent().eq(-1).find("button").keydown(function (e) {
        var tab = Tab(function () {
            seltOptionStep();
        }, e);
    })
}

function research() {
// infra
    var $infraRolling = $("#infraRolling");

    var $imgWrap = $infraRolling.find(".img_wrap"),
        $imgLi = $imgWrap.find("li");
    var $txtWrap = $infraRolling.find(".txt_wrap"),
        $txtLi = $txtWrap.find("li");
    var $thumbWrap = $infraRolling.find(".thumb_img"),
        $thumLi = $thumbWrap.find("li");
    var $prevBtn = $infraRolling.find(".prev_btn"),
        $nextBtn = $infraRolling.find(".next_btn");
    var _imgLength = $imgLi.length;

    var infraCrr = {};

    Object.defineProperty(infraCrr, 'number', {
        get: function () {
            return this.num || 0;
        },
        set: function (_index) {
            _index = _index % _imgLength;
            TweenMax.to($imgLi, .3, {opacity: 0, display: "none", ease: es_step});
            TweenMax.to($imgLi.eq(_index), 1, {opacity: 1, display: "block", ease: es_step});

            TweenMax.to($txtLi, .3, {y: 30, opacity: 0, display: "none"});
            TweenMax.to($txtLi.eq(_index), .3, {y: 0, opacity: 1, display: "block"});

            $thumLi.find("button").removeClass("active");
            $thumLi.eq(_index).find("button").addClass("active");
            this.num = _index;
        }
    });
    $nextBtn.click(function () {
        infraCrr.number++;
    });
    $prevBtn.click(function () {
        infraCrr.number--;
    });
    $thumLi.find("button").click(function () {
        var _this = $(this);
        var _index = _this.parent().index();

        infraCrr.number = _index;
    });
}

function scrollBg() {
    $(window).scroll(function () {
        $(".tit_wrap").each(function () {
            var offset = $(this).offset();
            var offset_top = offset.top;
            if (offset_top < winSc + winH / 1.5) {
                $(this).find("h1").addClass("on");
            }
        });
        if (winSc > 1 && winSc < winH) {
            TweenMax.to($subVisual, .1, {"background-position-y": (winSc / 10), ease: es_step});
        } else if (winSc === 0) {
            TweenMax.to($subVisual, .1, {"background-position-y": 0, ease: es_step});
        }
    });


    var $jsScrSec = $(".js-scr-sec"); // js-scr-sec

    var scrInnerStep = []; //
    function scrollEvent() {
        $window.scroll(function () {
            scrollMotion(winSc);
        });
        $jsScrSec.each(function () {
            var _this = $(this);
            var secTop = _this.offset().top;
            var secInner = secTop - (winH / 2) - 250;
            scrInnerStep.push([_this, secInner]);
        });

        function scrollMotion(_winSc) {
            $.each(scrInnerStep, function (i, v) {
                if (_winSc >= v[1]) {
                    if (v[0].motion === undefined) {
                        TweenMax.staggerTo(v[0].find(".js-scr-box"), .8, {
                            y: 0, opacity: 1, ease: es_step, className: '+=js-motion-end'
                        }, .2);
                        v[0].motion = true;
                    }
                }
            });
        }

        scrollMotion(winSc);
    }

    scrollEvent();
}


$(document).ready(function () {

    $('ul.nearTab li').click(function () {
        var tab_id = $(this).attr('data-tab');

        $('ul.nearTab li').removeClass('current');
        $('.tab-content').removeClass('current');

        $(this).addClass('current');
        $("#" + tab_id).addClass('current');
    })

})


$(document).ready(function () {

    $('.tab-reveiw a').click(function () {
        var tab_id = $(this).attr('data-tab');

        $('.tab-reveiw a').removeClass('on');
        $('.review_inner_cont').removeClass('on');

        $(this).addClass('on');
        $("#" + tab_id).addClass('on');
    })

})

$(document).ready(function () {

//
    if ($(".main_thum").length > 0) {
        $(".store_slide li").on("mouseenter", function () {
            $(this).addClass("over");
            $(this).on("mouseleave", function () {
                $(this).removeClass("over");
            });
        });

        $(".main_list li").on("mouseenter", function () {
            $(this).addClass("over");
            $(this).on("mouseleave", function () {
                $(this).removeClass("over");
            });
        });

        $(".main_cover dl").on("mouseenter", function () {
            $(this).addClass("over");
            $(this).on("mouseleave", function () {
                $(this).removeClass("over");
            });
        });
    }

//
    if ($(".thum").length > 0) {
        $(".thumbnail li").on("mouseenter", function () {
            $(this).addClass("over");
            $(this).on("mouseleave", function () {
                $(this).removeClass("over");
            });
        });

        $(".thumbnail dl").on("mouseenter", function () {
            $(this).addClass("over");
            $(".after_wrap").show();
            $(this).on("mouseleave", function () {
                $(this).removeClass("over");
            });
        });

        $(".ImgTit").on("mouseenter", function () {
            $(this).addClass("over");
            $(".after_img").show();
            $(this).on("mouseleave", function () {
                $(this).removeClass("over");
                $(".after_img").hide();
            });
        });
    }

});


$(document).ready(function () {
    $(window).scroll(function () {
        if ($(this).scrollTop() > 200) {
            $('.viewTop').fadeIn();
        } else {
            $('.viewTop').fadeOut();
        }
    });
    $('.viewTop').click(function () {
        $('html, body').animate({scrollTop: 0}, 400);
        return false;
    });
});


// Get the mopop
var mopop = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the mopop
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the mopop
if (btn != undefined) {
    btn.onclick = function () {
        mopop.style.display = "block";
    }
}

// When the user clicks on <span> (x), close the mopop
if (span != undefined) {
    span.onclick = function () {
        mopop.style.display = "none";
    }
}

// When the user clicks anywhere outside of the mopop, close it
window.onclick = function (event) {
    if (event.target == mopop) {
        mopop.style.display = "none";
    }
}
                                                              
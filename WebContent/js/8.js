var prevH = new Array;
var rePrevH;
var nowMgnbOpen = false;
var isHeadFix = false;
var lnbWidthSum = 0;
var faqWidthSum = 0;
var selTabWSum = 0;
var swiperWidthSum = 0;
var tabSwiperWidthSum = 0;
var faqN = -1;
var browser = ''; //2019-01-23 異붽�
var prevScrollTop = 0;


$(document).ready(function(){
    //�ㅻ뜑�ㅽ겕濡�
    headerScroll();
    checkBroswer(); //2019-01-23 異붽�

    //�고쁺 紐⑥뀡愿��� 諛곗뿴
    $(".companyD .historyD .historyList").each(function(q){
        var prevH_sub = new Array();
        if($(this).find(".bg").length > 0){
            $(".companyD .historyD .historyList").eq(q).find(".bg img").each(function(i){
                prevH_sub.push($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(i).css("top").split("p")[0])
                prevH.push(prevH_sub);
            });
        }else{
            prevH_sub.push(0)
            prevH.push(prevH_sub);
        }
    })
    rePrevH = prevH.reduce(function(a,b){
        if (a.indexOf(b) <0)a.push(b);
        return a;
    },[]);

    //gnb
    var mGnbN = -1;

    $("#mGnb .dep").each(function(q){
        $(this).find(".oneD").on("click", function(){
            if(mGnbN != q){
                $("#mGnb .dep").eq(mGnbN).removeClass("on");
                $("#mGnb .dep").eq(mGnbN).find(".twoD").slideUp(300);
                $("#mGnb .dep").eq(mGnbN).find("li > a.hasThr").removeClass("on");
                $("#mGnb .dep").eq(mGnbN).find("li > .thrD").slideUp(300);
                mGnbN = q;
                $("#mGnb .dep").eq(mGnbN).addClass("on");
                $("#mGnb .dep").eq(mGnbN).find(".twoD").slideDown(300);
            }else{
                $("#mGnb .dep").eq(mGnbN).removeClass("on");
                $("#mGnb .dep").eq(mGnbN).find(".twoD").slideUp(300);
                $("#mGnb .dep").eq(mGnbN).find("li > a.hasThr").removeClass("on");
                $("#mGnb .dep").eq(mGnbN).find("li > .thrD").slideUp(300);
                mGnbN = -1;
            }
        });
        //nowMgnbOpen = true;
    });

    //footer family
    familyD();

    // topBtn
    $(".topBtn").click(function(){
        $("html, body").stop().animate({"scrollTop": 0}, 300);
        $('.header').addClass('up');
    });
    $(window).scroll(function(){
        if($(window).scrollTop() > 75){
            $(".topBtn").fadeIn(300);
        }else{
            $(".topBtn").fadeOut(300);
        }
    });

    //濡쒕뱶�� �ㅻ뜑 �믪씠�↔린
    if ($('#gnb').is(':visible')) {
        $(".header").outerHeight("82px");
    }else{
        $(".header").height($(window).height()*0.08);
    }

    // �곸떆梨꾩슜 - 吏��먯꽌 �낅젰 - �꾩떆 ���� 踰꾪듉 臾멸뎄 2018-12-04 異붽�
    $('.recruitDiv .btnArea .floatBtn').click(function(){
        $('.recruitDiv .btnArea .floatBtn span').fadeIn(300);
        setTimeout(function(){ $('.recruitDiv .btnArea .floatBtn span').fadeOut(300); }, 1000);
    });

    //�좎젣�� hover
    $(".pdListD .list").each(function(){
        $(this).on("mouseenter", function(){
            TweenMax.to($(this).find(".bg"), 0.3, {opacity:1});
        });
        $(this).on("mouseleave", function(){
            TweenMax.to($(this).find(".bg"), 0.3, {opacity:0});
        });
    });

    var prevWidth1 = 832;
    var prevWidth2 = $(".companyD .historyD .fixImg").width();
    var prevWindowWidth = $(window).width();
    $(window).resize(function(){
        // �댁뒪/�몃줎蹂대룄 由ъ뒪�� �믪씠媛� �↔린
        if($(".photoList .listWrap .list.txtType").length > 0){

            var maxHeight = 0;

            $(".photoList .listWrap .list.txtType").each(function(){
                var curHeight = $(this).outerHeight();
                if(maxHeight < curHeight){
                    maxHeight = curHeight;
                }
            })
            //$(".photoList .listWrap .list").outerHeight(maxHeight);
        }

        //gnb
        if ($('#gnb').is(':visible')) {
            // pc �댁긽��
            // �ㅽ겕濡�
            if($(".scrollBox").length != 0){
                $(".scrollBox").mCustomScrollbar('destroy');
                $(".scrollBox").mCustomScrollbar({
                    scrollInertia:50,
                    advanced:{
                        updateOnContentResize: true
                    }
                });
            }

            $('#mGnb').css('left','100%');
            $("#mGnb .dep").removeClass("on");
            $("#mGnb .dep").find(".twoD").slideUp(300);
            $("#mGnb .dep").find("li > a.hasThr").removeClass("on");
            $("#mGnb .dep").find("li > .thrD").slideUp(300);
            mGnbN = -1;
            $('.mobileBg').hide();
            $(".header").outerHeight("82px");
        } else {
            // mobile �댁긽��
            if($(".scrollBox").length != 0){
                $(".scrollBox").mCustomScrollbar('destroy');
            }

            if(prevWindowWidth != $(window).width()){
                $(".header").height($(window).height()*0.08);
                isHeadFix = false;
                if ($('.newsFeedPop').is(':visible') == true){
                    $("#wrap").css({"overflow":"hidden","height":"100%"});
                    isHeadFix = true;
                }else if($('.lyPop').is(':visible') == true){
                    $("#wrap").css({"overflow":"hidden","height":"100%"});
                    isHeadFix = true;
                }else{
                    $("#wrap").css({"overflow":"","height":""});

                    isHeadFix = false;
                }
            }
            $(".header .utill .sch").removeClass('on');

            //<editor-fold desc="## 사이즈 변경에 의한 클로즈를 수정함 ##">
            var inputSearchObj = $('input[name="totalSearchFuncKeyword"]');            
            if(inputSearchObj != undefined){
                if(inputSearchObj.is(":focus") == false){
                    $('.searchPop').hide();        
                }
            }else{
                $('.searchPop').hide();
            }
            //</editor-fold>
            
            if($(".mainPop").is(":visible")){
                isHeadFix = true;
            }else{
                isHeadFix = false;
            }
        }

        if(nowMgnbOpen){
            $("#mGnb").outerHeight($(window).height());
            isHeadFix = true;
        }

        // �ъ뾽�먰쁽�� - �좏넻 �ㅼ��댄띁 �� �⑹뿉 �곕씪 �띿꽦 蹂�寃�
        swiperWidth();

        // twoD - lnbSwiper
        lnbWidth();

        //�꾩껜�쒗뭹 - swiper
        tabSwiperWidth();

        // faqSwiper
        faqWidth();

        selWidth();

        //�띾낫�쇳꽣
        $(".photoList .listWrap .list .img").height($(".photoList .listWrap .list .img").width());
        /* 2019-01-02 �믪씠媛� �ㅻ쪟濡� 二쇱꽍
        $(".schBox .listWrap .list .img").height($(".schBox .listWrap .list .img").width());*/
        $(".brandView .newsD").not(".view0205").find(".list .img").height($(".brandView .newsD").not(".view0205").find(".list .img").width());

        //愿묎퀬�먮즺��
        $(".printList .listWrap .list .img").height($(".printList .listWrap .list .img").width() * 421/281)

        //湲�濡쒕쾶 �ъ뾽
        $(".imgCon").height($(".imgCon .img").height());

        //吏��띻��κ꼍��_�ы쉶怨듯뿄_湲곕��쒕룞
        $(".socialArea .boxArea").each(function(q){
            if(q%2 != 0){
                $(this).addClass("left");
            }
        });

        if($(window).width() > 1263){
            $(".productDiv .brandView .kindD .listDiv").css("width","auto");
            $(".productDiv .brandView .kindD .list").css("width","330px");

            // �섏긽 諛� �몄쬆 - �몄쬆 �댁뿭
            $('.awardDiv.agentAward .txtArea').height($('.awardDiv.agentAward .imgArea').height());
        }else{
            $(".productDiv .brandView .kindD .listDiv").each(function(){
                $(this).css("width","100%");
                $(this).find(".list").css("width", $(".kindD").width() * 3/10);
            })
        }

        if($(window).width() > 964){
            // 吏��먯꽌 �묒꽦  - �꾩떆���� 踰꾪듉
            if($('.btnArea').length > 0){
                if($(window).scrollTop() + $(window).height() - $('.btnArea').height() - 30 > $('.btnArea').offset().top){
                    $('.floatBtn').css({'position':'absolute','bottom':'0'});
                }
                $(window).scroll(function(){
                    if($('.floatBtn').length!=0){
                        if($(window).scrollTop() + $(window).height() - $('.btnArea').height() - 30 > $('.btnArea').offset().top){
                            $('.floatBtn').css({'position':'absolute','bottom':'0'});
                        }else{
                            $('.floatBtn').css({'position':'fixed','bottom':'30px'});
                        }
                    }
                })
            }

            //�댁뒪�쇰뱶�앹뾽
            if(!$(".newsFeedPop").hasClass("mCustomScrollbar")){
                $(".newsFeedPop").mCustomScrollbar({
                    mouseWheel:{
                        scrollAmount:1000000
                    },
                    scrollInertia:1000,
                    advanced:{
                        updateOnContentResize: true
                    }
                });
            }
            /*
            if($(".newsFeedPop .contentD").hasClass("mCustomScrollbar")){
                $(".newsFeedPop .contentD").mCustomScrollbar("destroy");
            }*/
        }else{
            // 吏��먯꽌 �묒꽦  - �꾩떆���� 踰꾪듉
            $(window).scroll(function(){
                if($('.floatBtn').length!=0){
                    $('.floatBtn').css({'position':'static','bottom':'initial'});
                }
            })

            // �댁뒪�쇰뱶�앹뾽
            if(!$(".newsFeedPop").hasClass("mCustomScrollbar")){
                $(".newsFeedPop").mCustomScrollbar({
                    mouseWheel:{
                        scrollAmount:1000000
                    },
                    scrollInertia:1000,
                    advanced:{
                        updateOnContentResize: true
                    }
                });
            }
            /*
            if(!$(".newsFeedPop .contentD").hasClass("mCustomScrollbar")){
                $(".newsFeedPop .contentD").mCustomScrollbar({
                    scrollInertia:50,
                    advanced:{
                        updateOnContentResize: true
                    }
                });
            }
            */
            //�앺뭹�덉쟾�쒖뒪��
            $(".manageDiv .foodList > div .imgW img").each(function(){
                if($(this).get(0).naturalWidth >= $(".manageDiv .foodList > div .imgW").width()){
                    $(this).css("width","100%");
                }else{
                    $(this).css("width", "auto");
                    $(this).css("maxWidth", $(this).get(0).naturalWidth);
                }
            });
        }

        if($(window).width() > 964){
            //ceo�몄궗留�
            if($(".companyD .ceoD").length > 0){
                $(".companyD .ceoImg").css("width", prevWidth1 - ( 360 - $(".companyD .ceoD").offset().left));
                $(".companyD .ceoD .info").css("top", $(".companyD .ceoImg").height() - 55);
            }

            // �뚯궗媛쒖슂
            if($(".companyD .comIntroD").length > 0){
                $(".companyD .comIntroD .intImg").css("left", $(".companyD .comIntroD .inner").offset().left + 710);
                //$(".companyD .ceoD .info").css("top", $(".companyD .ceoImg").height() - 55);
            }

            //history
            /*
            if($(".companyD .historyD").length > 0){
                $(".companyD .scrollD").height($(window).height() - parseInt($(".companyD.history").css("padding-top").split("p")[0]))
                $(".companyD .historyD .historyListD").css("padding-top",$(window).height() - 450);
                $(".companyD .fixArea").height($(window).height());
                $(".companyD.history").css("height", $(window).height())
                $(".companyD.history .historyList").css("margin-top", $(window).height())
            }*/
        }else if($(window).width() > 741 && $(window).width() <= 964){
            if($(".companyD .ceoD").length > 0){
                $(".companyD .ceoImg").css("width", "383px");
                $(".companyD .ceoD .info").css({"top":"206px", "left":"5%"});
            }
            // table �ㅽ겕濡� �덈퉬
            $('.tableW.w600').css('width','auto');

            // gnb width �덈퉬
            if($(".mobileBg").css("display") == "block"){
                $(".header #mGnb").css({'left':'55%', 'width':'45%'});
            }
        }else{
            if($(".companyD .ceoD").length > 0){
                $(".companyD .ceoImg").css("width", "100%");
                $(".companyD .ceoD .info").css({"top":"0", "left":"0"});
            }
            // table �ㅽ겕濡� �덈퉬
            $('.tableW.w600').width($(window).width()+150);

            // gnb width �덈퉬
            if($(".mobileBg").css("display") == "block"){
                $(".header #mGnb").css({'left':'0', 'width':'100%'});
            }
        }


        //�고쁺
        if($(".companyD .historyD").length > 0){
            if($(window).width() > 1263){
                $(".companyD .historyD .fixImg").css("width", prevWidth2 - ( 360 - $(".companyD .historyD .historyList .inner").offset().left));
            }else if($(window).width() > 741){
                $(".companyD .historyD .fixImg").css("width", prevWidth2 - ( 900 - $(".companyD .historyD .historyList .inner .list").offset().left));
            }else{
                $(".companyD .historyD .fixImg").css("width", "100%");
            }

            if($(window).width() < 741){
                $(".companyD .historyD .fixImg img").css("display","inline");
            }
        }
        // table �ㅽ겕濡� �덈퉬
        $('.tableW.w600').css('width','auto');

        //釉뚮옖�� 諛곕꼫
        if($(".productDiv .brandView .bannerD").length > 0){
            $(".productDiv .brandView .bannerD").css("margin-left", $(".productDiv .brandView .bannerTxt").offset().left);
        }

        //釉뚮옖�� �덉뒪�좊━
        if($(".productDiv .brandView .bHistory").length > 0){
            $(".productDiv .brandView .bHistory .swiper-container").css("width", $(window).width());
            $(".productDiv .brandView .bHistory .swiper-container").css("padding-left", $(".productDiv .brandView .bHistory .inner").offset().left);
            $(".productDiv .brandView .bHistory .swiper-container").css("padding-right", $(".productDiv .brandView .bHistory .inner").offset().left);
            $(".productDiv .brandView .bHistory .swiper-scrollbar").css("left", $(".productDiv .brandView .bHistory .inner").offset().left);
        }

        //釉뚮옖�� �곸꽭 pageBtn
        if($(".pageBtn").length > 0){
            $("#cBody").css("padding-bottom", $(".pageBtn").height());
        }

        $(".productDiv .brandView .tvcfD .cfDiv .selectArea .videoArea").height($(".productDiv .brandView .tvcfD .cfDiv .selectArea .videoArea").width() * 509/904)
    });$(window).resize();

    // pc gnb
    $("#gnb").on("mouseenter", function(){
        if(!isHeadFix){
            //$("#gnb .twoD").slideDown(200);
            //$(".header .twoDBg").slideDown(200, function(){
            //	$(".header #gnb .infoD").show()
            //});
            $("#gnb .twoD").show();
            $(".header .twoDBg").show();
        }
    });
    $("#gnb").on("mouseleave", function(){

        //$("#gnb .twoD").stop().slideUp(200);
        //$(".header .twoDBg").stop().slideUp(200);
        //$(".header #gnb .infoD").hide();
        $("#gnb .twoD").hide();
        $(".header .twoDBg").hide();
    });
    
    
    $("#gnb").focusin(function(){
        if(!isHeadFix){
            //$("#gnb .twoD").slideDown(200);
            //$(".header .twoDBg").slideDown(200, function(){
            //	$(".header #gnb .infoD").show()
            //});
            $("#gnb .twoD").show();
            $(".header .twoDBg").show();
        }
    });
    
    $("#gnb").focusout(function(){
        //$("#gnb .twoD").stop().slideUp(200);
        //$(".header .twoDBg").stop().slideUp(200);
        //$(".header #gnb .infoD").hide();
        // != 0
        if($(".twoD").outerHeight() <= 0){
            $("#gnb .twoD").show();
            $(".header .twoDBg").show();
        }
    });    
    
    $(".twoD li:last-child").focusout(function(){
        $("#gnb .twoD").hide();
        $(".header .twoDBg").hide();
    });    
    
    $(".closeBtn").click(function(){
        $(".sch").focus();
    });        
  

    // mobile gnb
    // $("#mGnb").removeClass("pc").css("left", "100%");
    // $(".header #mGnb .dep").removeClass("on");
    // $(".header #mGnb .dep .twoD").hide();

    mobileThrD();
    $(".header .mGnbOpen").click(function(){
        if($(window).width() <= 1280 && $(window).width() > 741){
            TweenMax.to($("#mGnb"), 0.5, {left:"55%", ease:Power3.easeOut});
        }else if($(window).width() <= 741){
            TweenMax.to($("#mGnb"), 0.5, {left:0, ease:Power3.easeOut});
        }
        $('#mGnb').outerHeight($(window).height());
        $(".header #mGnb .dep").removeClass("on");
        $(".header #mGnb .dep .twoD").hide();
        $(".mobileBg").fadeIn(300);
        prevScrollTop = $(window).scrollTop();
        $("#wrap").css({"overflow":"hidden","height":"100%"});
        nowMgnbOpen = true;
        gnbN = -1;
        isHeadFix = true;
    });

    $(".header #mGnb .mClose").click(function(){
        $("#mGnb .dep").removeClass("on");
        $("#mGnb .dep").find(".twoD").slideUp(0);
        $("#mGnb .dep").find("li > a.hasThr").removeClass("on");
        $("#mGnb .dep").find("li > .thrD").slideUp(0);
        mGnbN = -1;
        TweenMax.to($("#mGnb"), 0.5, {left:"100%", ease:Power3.easeOut});
        $(".mobileBg").fadeOut(300);
        $("#wrap").css({"overflow":"","height":""});
        $("html, body").animate({"scrollTop":prevScrollTop},0);
        nowMgnbOpen = false;
        isHeadFix = false;
    });

    $("#mGnb .dep .twoD > li > a.hasThr").each(function(){
        $(this).on("click",function(){
            if(!$(this).hasClass("on")){
                $("#mGnb .dep .twoD > li > a.hasThr").removeClass("on");
                $("#mGnb .dep .twoD > li > a.hasThr").next(".thrD").slideUp(300);
                $(this).addClass("on");
                $(this).next(".thrD").slideDown(300);
            }else{
                $(this).removeClass("on");
                $(this).next(".thrD").slideUp(300);
            }
        });
    });


    //寃��됱갹 �닿린
    $(".header .utill .sch").click(function(){
        if(!$(this).hasClass("on")){
            $(this).addClass("on");
            $(".searchPop").stop(true, true).fadeIn(300);
            prevScrollTop = $(window).scrollTop();
            $("#wrap").css({"overflow":"hidden","height":"100%"});
        }
        isHeadFix = true;
    });


    //寃��됱갹 �リ린
    $(".searchPop .closeBtn").click(function(){
        $(".header .utill .sch").removeClass("on");
        $(".searchPop").stop(true, true).fadeOut(300);
        $("#wrap").css({"overflow":"","height":""});
        $("html, body").animate({"scrollTop":prevScrollTop},0);
        isHeadFix = false;
    });

    /*
    //�댁뒪�쇰뱶 �닿린
    $(".newsFeed").click(function(){
        $(".newsFeedPop").stop(true, true).fadeIn(400);
        $(".newsFeedPop .closeBtn").stop(true, true).fadeIn(400, function(){
            TweenMax.to($(".newsFeedPop .headTit"), 0.6, {opacity:1, ease:Power3.easeOut});
            TweenMax.to($(".newsFeedPop .listDiv"), 0.6, {opacity:1, delay:0.2, ease:Power3.easeOut});
        });
        $(".newsFeedPop").mCustomScrollbar({
            scrollInertia:50,
            advanced:{
                updateOnContentResize: true
            }
        });
        $("body").css("overflow","hidden");
        if($(".newsFeedPop .listDiv").length > 0){
            $(".newsFeedPop .listDiv").isotope({
                itemSelector: ".newsFeedPop .listDiv .list",
            });
            $(window).resize();
        }
        $(".newsFeedPop .listDiv .list").each(function(){
            if(!$(this).find("img").length > 0){
                $(this).find(".txtArea").css("border-top","1px solid rgba(255, 255, 255, 0.4)");
            }
        });
    }); */


    //�댁뒪�쇰뱶 �붾낫湲�
    $(".newsFeedPop .listDiv .moreBt").hover(function(){
        jQuery(this).find("img").attr("src", jQuery(this).find("img").attr("src").replace(".png", "_on.png"));
    }, function(){
        jQuery(this).find("img").attr("src", jQuery(this).find("img").attr("src").replace("_on.png", ".png"));
    });

    //�댁뒪�쇰뱶 �リ린
    $(".newsFeedPop .closeBtn").click(function(){
        $(".newsFeedPop").stop(true, true).fadeOut(400);
        $(".newsFeedPop .closeBtn").stop(true, true).fadeOut(400, function(){
            TweenMax.to($(".newsFeedPop .headTit"), 0, {opacity:0, ease:Power3.easeOut});
            TweenMax.to($(".newsFeedPop .listDiv"), 0, {opacity:0, ease:Power3.easeOut});
        });
        $(".newsFeedPop").mCustomScrollbar("destroy");
        if(!$(".searchPop").is(":visible")){
            $("#wrap").css({"overflow":"","height":""});
            $("html, body").animate({"scrollTop":prevScrollTop},0);
        }
    });

    //湲�濡쒕쾶 �ъ뾽
    $(".globalDiv .tab a").each(function(i){
        $(this).click(function(){
            if(!$(this).hasClass("on")){
                $(".globalDiv .tab a").removeClass("on");
                $(".globalDiv .imgCon .img").fadeOut(500);
                $(this).addClass("on");
                $(".globalDiv .imgCon .img").eq(i).fadeIn(500);
            }else{

            }
        })
    });

    // 寃��됱쁺�� ���됲듃 �대┃ ��
    $(".srchSel > a").on('click', function(){
        if(!$(this).parent('.srchSel').hasClass('disabled')){
            if(!$(this).hasClass("on")){
                $(".srchSel > a, .selectTab > a").removeClass("on");
                $(".srchSel > div, .selectTab > div").slideUp(300);
                $(this).addClass("on");
                $(this).next().stop().slideDown(300);
            }else{
                $(this).removeClass("on");
                $(this).next().slideUp(300);
            }
        }
    });

    $(".srchSel > div .selectScroll a").on('click', function(){
        var selTxt = $(this).text();
        $(this).siblings(".active").removeClass("active");
        $(this).addClass("active");
        if(!$(this).parents('.srchSel').hasClass('attached')){
            $(this).parent().parent().siblings("a").removeClass("on");
            $(this).parent().parent().siblings("a").text(selTxt).removeClass("on");
            $(this).parent().parent().slideUp(300);
            // 2019-01-04 吏��먯꽌 �낅젰 - 蹂묒뿭�ы빆 援щ텇 異붽�
            if($(this).parents('.srchSel').hasClass('military')){
                if($(this).index()==0){
                    $('.inputForm .list .info.optActive').each(function(){
                        $(this).find('input').prop("disabled", false);
                        $(this).find('.srchSel').removeClass('disabled');
                    });
                }else{
                    $('.inputForm .list .info.optActive').each(function(){
                        $(this).find('input').prop("disabled", true);
                        $(this).find('.srchSel').addClass('disabled');
                    });
                }
            }
        }
    });




    // �좏넻 ���됲듃 �대┃ ��
    $(".selectTab > a").click(function(){
        if($(this).parents(".selectTab").hasClass("mobile")){
            if(!$(this).hasClass("on")){
                $(".srchSel > a, .selectTab > a").removeClass("on");
                $(".srchSel > div, .selectTab > div").slideUp(300);
                $(this).addClass("on");
                $(this).next().stop().slideDown(300);
            }else{
                $(this).removeClass("on");
                $(this).next().slideUp(300);
            }
        }
    })

    $(".selectTab > div .selectScroll a").on('click', function(){
        if($(this).parents(".selectTab").hasClass("mobile")){
            var selTxt = $(this).text();
            $(this).siblings(".active").removeClass("active");
            $(this).addClass("active");
            $(this).parent().parent().siblings("a").text(selTxt).removeClass("on");
            $(this).parent().parent().slideUp(300);
        }
    });

    // 媛�濡� �ㅽ겕濡�
    if($(".horizontalScroll").length != 0){
        $(".horizontalScroll").mCustomScrollbar({
            axis:"x",
            scrollInertia:50,
            advanced:{
                updateOnContentResize: true
            }
        });
    }

    $('.playBtn').on('click', function(){
        $('.videoArea .beforePlay').hide();
    });

    /* �덉씠�댄뙘�� �닿린,�リ린 愿��� */
    //�リ린
    $('.lypopClose').on('click', function(){
        $("#wrap").css({"overflow":"","height":""});
        $("html, body").animate({"scrollTop":prevScrollTop},0);
        $('.lyPop').stop().fadeOut(300);
        isHeadFix = false;
    });

    //tv愿묎퀬
    $('.videoList .listWrap .list, .videoList .listWrapContainer .list, .printList .listWrapContainer .list, .printList .listWrap .list, .innovDiv .ingListDiv.type2 .list, .innovDiv .ingListDiv.type3 .list, .listWrap.video .list, .listWrap.print .list ').each(function(q){
        $(this).click(function(){
            $('.lyPop').stop().fadeIn(300);
            prevScrollTop = $(window).scrollTop();
            $("#wrap").css({"overflow":"hidden","height":"100%"});
            isHeadFix = true;
        });
    });

    //�쒗뭹
    $('.subBody .productDiv .allPdViewDiv .list').click(function(){
        $('.lyPop').stop().fadeIn(300);
        prevScrollTop = $(window).scrollTop();
        $("#wrap").css({"overflow":"hidden","height":"100%"});
        isHeadFix = true;
    });

    //�대끂踰좎씠��
    $(".innovDiv .actionIdeaD .ideaVisual .txtArea .list .btn").click(function(){
        $(".lyPop").stop().fadeIn(300);
        prevScrollTop = $(window).scrollTop();
        $("#wrap").css({"overflow":"hidden","height":"100%"});
        isHeadFix = true;
    });

    // 怨좉컼遺덈쭔 泥섎━�덉감
    $(".complainDiv .complainProcess .topArea a").click(function(){
        $(".lyPop").stop().fadeIn(300);
        prevScrollTop = $(window).scrollTop();
        $("#wrap").css({"overflow":"hidden","height":"100%"});
        isHeadFix = true;
    });

    //怨좉컼吏��� - faq �꾩퐫�붿뼵
    $('.accorDiv .list .qArea').on("click", function(){
        q = $(".accorDiv .list .qArea").index(this);
        if(q!=faqN){
            $('.accorDiv .list .aArea').stop(true, true).slideUp(300);
            $('.accorDiv .list').removeClass('on');
            TweenMax.to($('.accorDiv .list .qArea').eq(faqN).find('.arrIcon'), 0.3, {rotation:0});
            faqN = q;
            $('.accorDiv .list').eq(faqN).addClass('on');
            $('.accorDiv .list .aArea').eq(faqN).stop(true, true).slideDown(300);
            TweenMax.to($('.accorDiv .list .qArea').eq(faqN).find('.arrIcon'), 0.3, {rotation:180});
        }else{
            $('.accorDiv .list .aArea').eq(faqN).stop(true, true).slideUp(300);
            $('.accorDiv .list').eq(faqN).removeClass('on');
            TweenMax.to($('.accorDiv .list').eq(faqN).find('.qArea p'), 0.3, {rotation:0});
            faqN = -1;
        }
    });


    // �ъ뾽�� �꾪솴 > 怨듭옣�뚭컻 �꾩퐫�붿뼵
    //TweenMax.to($(".bizAco .acoBtn").eq(0).find(".arrowIcon") ,0, {rotation:180});
    $(".bizAco .acoBtn").each(function(i){
        $(this).click(function(){
            if(!$(this).hasClass("on")){
                $(".bizAco .acoBtn").removeClass("on");
                TweenMax.to($(".bizAco .acoBtn").find(".arrowIcon") , 0.3, {rotation:0});
                $(".bizAco .acoCon").stop(true, true).slideUp(300);
                $(this).addClass("on");
                TweenMax.to($(".bizAco .acoBtn").eq(i).find(".arrowIcon") , 0.3, {rotation:180});
                $(this).next(".acoCon").stop(true, true).slideDown(300);
            }else{
                $(this).removeClass("on");
                TweenMax.to($(".bizAco .acoBtn").eq(i).find(".arrowIcon") , 0.3, {rotation:0});
                $(this).next(".acoCon").stop(true, true).slideUp(300);

            }
        })
    });


    // lnb - twoD - lnbSwiper
    $(".lnbSwiper .swiper-slide").each(function(q){
        lnbWidthSum += $(".lnbSwiper .swiper-slide").eq(q).width() + 30;
    });
    // �꾩껜�쒗뭹 - Swiper
    $(".tabArea .swiper-slide").each(function(q){
        tabSwiperWidthSum += $(".tabArea .swiper-slide").eq(q).outerWidth() + 30;
    });

    // �ъ뾽�먰쁽�� - �좏넻 �ㅼ��댄띁 �� �덈퉬 �� 援ы븯湲�
    $(".swiperArea .swiper-slide").each(function(q){
        swiperWidthSum += $(".swiperArea .swiper-slide").eq(q).width() + 35;
    });


    // lnb �덈퉬 �⑹뿉 �곕씪 �띿꽦 蹂�寃�
    lnbWidth();
    //�꾩껜�쒗뭹 - swiper
    tabSwiperWidth();
    //�ъ뾽�먰쁽�� - �좏넻 �ㅼ��댄띁 �� �⑹뿉 �곕씪 �띿꽦 蹂�寃�
    swiperWidth();



    // lnb
    var lnbSwiper = new Swiper('.lnbSwiper', {
        slidesPerView: 'auto',
        freeMode: true
    });
    if($('.lnbSwiper').length!=0){
        lnbSwiper.slideTo($(".subVisual .twoD div > a.on").index(), 0, true);
    }

    //�꾩껜�쒗뭹
    var tabSwiper = new Swiper('.tabArea', {
        slidesPerView: 'auto',
        freeMode: true,
    });
    // �ъ뾽�� �꾪솴
    var companySwiper = new Swiper('.introDiv.company1 .introD .listWrap', {
        slidesPerView: 'auto',
        freeMode: true,
    });



    $(window).load(function(){
        // �섏긽 諛� �몄쬆 - �몄쬆 �댁뿭
        $('.awardDiv.agentAward .txtArea').height($('.awardDiv.agentAward .imgArea').height());

        // �ъ뾽�먰쁽�� - �좏넻 �� �덈퉬 �� 援ы븯湲�
        $('.selectTab .selectScroll a').each(function(q){
            selTabWSum += $('.selectTab .selectScroll a').eq(q).outerWidth();
        });

        // faqSwiper
        $(".faqSwiper .swiper-slide").each(function(q){
            faqWidthSum += $(".faqSwiper .swiper-slide").eq(q).width() + 60;
        });





        // faqSwiper �덈퉬 �⑹뿉 �곕씪 �띿꽦 蹂�寃�
        faqWidth();

        // ���됲듃諛뺤뒪 紐⑤컮��
        selWidth();

        //�꾩껜�쒗뭹 �곸뿭
        if($(".allPdViewDiv").length > 0){
            $(".allPdViewDiv").isotope({
                itemSelector: ".allPdViewDiv .list",
            });
            $(window).resize();
        }

        /* Swiper */
        //�뚯궗媛쒖슂
        var swiper = new Swiper('.companyD .comIntroD .inforDiv.thr .swiper-container', {
            slidesPerView: 'auto',
            pagination: {
                el: '.swiper-pagination',
                clickable: true,
            },
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
        });



        // 釉뚮옖�쒖긽�� �덉뒪�좊━
        var brandSwiper = new Swiper('.bHistory .swiper-container', {
            slidesPerView: 'auto',
            spaceBetween: 30,
            freeMode: true,
            scrollbar: {
                el: '.swiper-scrollbar',
                draggable : true,
                snapOnRelease: false,
                dragsize:10,
            },
        });



        //釉뚮옖�� �곸꽭 �곸긽紐⑸줉 (媛쒕컻�붿껌�쇰줈 二쇱꽍)
        /* var brand2Swiper = new Swiper('.tvcfD .swiper-container', {
            slidesPerView: '4',
            spaceBetween: 20,
            scrollbar: {
                el: '.swiper-scrollbar',
                draggable : true,
                snapOnRelease: false,
                dragsize:10,
            },
            breakpoints: {
                    984 : {
                        slidesPerView: '2',
                        spaceBetween: '3%',
                    }
            },
        }); */


        //�쒓렇
        var swiper = new Swiper('.swiperArea', {
            slidesPerView: 'auto',
        });

        //�대끂踰좎씠�� - �ㅽ뻾�꾨즺 �꾩씠�붿뼱 swiper
        var swiper = new Swiper('.ideaVisual .swiper-container', {
            pagination: {
                el: '.swiper-pagination',
                clickable: true,
            },
            on: {
                slideChangeTransitionStart: function(){
                    $(".innovDiv .actionIdeaD .ideaVisual .txtArea .list").eq(this.previousIndex).removeClass("on");
                    //$(".innovDiv .actionIdeaD .ideaVisual .txtArea .list").eq(this.activeIndex).find(".type").css("top", $(".innovDiv .actionIdeaD .ideaVisual .txtArea .list").eq(this.activeIndex).find(".tit").height() + 96);
                },
                slideChangeTransitionEnd: function(){
                    $(".innovDiv .actionIdeaD .ideaVisual .txtArea .list").eq(this.activeIndex).addClass("on");

                    //$(".innovDiv .actionIdeaD .ideaVisual .txtArea .list").eq(this.activeIndex).find(".type").css("top", $(".innovDiv .actionIdeaD .ideaVisual .txtArea .list").eq(this.activeIndex).find(".tit").height() + 66);
                    //$(".innovDiv .actionIdeaD .ideaVisual .txtArea .list").eq(this.activeIndex).find(".date").css("top", $(".innovDiv .actionIdeaD .ideaVisual .txtArea .list").eq(this.activeIndex).find(".tit").height() + 116);
                },
            }
        });

    });

    $(window).scroll(function(){
        scrollMotion();
    });$(window).scroll();

});


function scrollMotion(){
    //湲곗뾽�뚭컻_�뚯궗媛쒖슂
    if($(".companyD .comIntroD .inforDiv").length > 0){
        //inforDiv.one
        if($(window).scrollTop() > $(".companyD .comIntroD .inforDiv.one").offset().top - 350){
            $(".companyD .comIntroD .inforDiv.one .listDiv .list").each(function(q){
                TweenMax.to($(".companyD .comIntroD .inforDiv.one .listDiv .list").eq(q), 0.9, {top:0, opacity:1, delay:0.3*q + 0.2, ease:Power3.easeOut});
            });
            TweenMax.to($(".companyD .comIntroD .inforDiv.one .txtArea .tit"), 0.9, {left:0, opacity:1, delay:1.2, ease:Power3.easeOut});
            TweenMax.to($(".companyD .comIntroD .inforDiv.one .txtArea .txt"), 0.9, {left:0, opacity:1, delay:1.4, ease:Power3.easeOut});
        }

        //inforDiv.two
        /* 2018-12-21 �붿옄�� 蹂�寃쎌쑝濡� 二쇱꽍
        if($(window).scrollTop() > $(".companyD .comIntroD .inforDiv.two").offset().top - 250){
            $(".companyD .comIntroD .inforDiv.two .listDiv .list").each(function(q){
                TweenMax.to($(".companyD .comIntroD .inforDiv.two .listDiv .list").eq(q), 0.9, {top:0, opacity:1, delay:0.3*q + 0.2, ease:Power3.easeOut});
            });
            TweenMax.to($(".companyD .comIntroD .inforDiv.two .txtArea .tit"), 0.9, {left:0, opacity:1, delay:1, ease:Power3.easeOut});
            TweenMax.to($(".companyD .comIntroD .inforDiv.two .txtArea .txt"), 0.9, {left:0, opacity:1, delay:1.2, ease:Power3.easeOut});
        }*/

        //inforDiv.thr
        // if($(window).scrollTop() <= $(".companyD .comIntroD .inforDiv.thr").offset().top - 600){
        // 	TweenMax.to($(".companyD .comIntroD .inforDiv.thr"), 1.2, {backgroundPosition:"50% 0%", ease:Power3.easeOut});
        // }else if($(window).scrollTop() > $(".companyD .comIntroD .inforDiv.thr").offset().top - 600 && $(window).scrollTop() <= $(".companyD .comIntroD .inforDiv.thr").offset().top - 400){
        // 	TweenMax.to($(".companyD .comIntroD .inforDiv.thr"), 1.2, {backgroundPosition:"50% 20%", ease:Power3.easeOut});
        // }else if($(window).scrollTop() > $(".companyD .comIntroD .inforDiv.thr").offset().top - 400 && $(window).scrollTop() <= $(".companyD .comIntroD .inforDiv.thr").offset().top - 200){
        // 	TweenMax.to($(".companyD .comIntroD .inforDiv.thr"), 1.2, {backgroundPosition:"50% 40%", ease:Power3.easeOut});
        // }else if($(window).scrollTop() > $(".companyD .comIntroD .inforDiv.thr").offset().top - 200 && $(window).scrollTop() <= $(".companyD .comIntroD .inforDiv.thr").offset().top){
        // 	TweenMax.to($(".companyD .comIntroD .inforDiv.thr"), 1.2, {backgroundPosition:"50% 60%", ease:Power3.easeOut});
        // }else if($(window).scrollTop() > $(".companyD .comIntroD .inforDiv.thr").offset().top && $(window).scrollTop() <= $(".companyD .comIntroD .inforDiv.thr").offset().top + 200){
        // 	TweenMax.to($(".companyD .comIntroD .inforDiv.thr"), 1.2, {backgroundPosition:"50% 80%", ease:Power3.easeOut});
        // }else{
        // 	TweenMax.to($(".companyD .comIntroD .inforDiv.thr"), 1.2, {backgroundPosition:"50% 100%", ease:Power3.easeOut});
        // }

        //inforDiv.fou
        $(".companyD .comIntroD .inforDiv.fou .lineDiv .line").each(function(q){
            if($(window).scrollTop() > $(".companyD .comIntroD .inforDiv.fou .lineDiv .line").eq(q).offset().top - 450){
                if(q%2 != 0){
                    TweenMax.to($(".companyD .comIntroD .inforDiv.fou .lineDiv .line").eq(q).find(".img"), 1.1, {top:0, opacity:1, ease:Power3.easeOut});
                    TweenMax.to($(".companyD .comIntroD .inforDiv.fou .lineDiv .line").eq(q).find(".txtArea"), 1.1, {top:0, opacity:1, delay:0.3, ease:Power3.easeOut});
                }else{
                    TweenMax.to($(".companyD .comIntroD .inforDiv.fou .lineDiv .line").eq(q).find(".img"), 1.1, {top:0, opacity:1, delay:0.3, ease:Power3.easeOut});
                    TweenMax.to($(".companyD .comIntroD .inforDiv.fou .lineDiv .line").eq(q).find(".txtArea"), 1.1, {top:0, opacity:1, ease:Power3.easeOut});
                }
            }
        });
    }

    //湲곗뾽�뚭컻_�ъ뾽�� �꾪솴
    // if($(".introDiv.company1 .introD").length > 0){
    // 	if($(window).scrollTop() + $(window).height() - 100 > $(".introDiv.company1 .listDiv").offset().top){
    // 		$(".introDiv.company1 .introD .listDiv .list").each(function(i){
    // 			TweenMax.to($(".introDiv.company1 .introD .listDiv .list").eq(i), 0.9 , {top:0, opacity:1, delay:0.3*i + 0.2, ease:Power3.easeOut});
    // 		});
    // 	}
    // }

    //湲곗뾽�뚭컻 �고쁺
    if($(".companyD .historyD").length > 0){
        //怨좎젙�대�吏�
        if($(window).scrollTop() < $(".companyD .historyD").offset().top && $(window).scrollTop() >= 0){
            $(".companyD .historyD .fixImg").css("position","absolute");
        }else if($(window).scrollTop() > $(".companyD .historyD").offset().top && $(window).scrollTop() + $(".companyD .historyD .fixImg:last").height() < $("#footer").offset().top - 150){
            $(".companyD .historyD .fixImg").css({"position":"fixed","top":"0", "bottom":"inherit"});
        }else{
            $(".companyD .historyD .fixImg").css({"position":"absolute", "top":"inherit", "bottom": "0"});
        }

        //�고쁺蹂� �대�吏�
        if($(window).width() > 741){
            if($(window).scrollTop() < $(".companyD .historyD .historyList").eq(1).offset().top && $(window).scrollTop() >= 0 ){
                $(".companyD .historyD .fixImg img").hide();
                $(".companyD .historyD .fixImg img").eq(0).show();
            }else if($(window).scrollTop() < $(".companyD .historyD .historyList").eq(2).offset().top && $(window).scrollTop() >= $(".companyD .historyD .historyList").eq(1).offset().top){
                $(".companyD .historyD .fixImg img").hide();
                $(".companyD .historyD .fixImg img").eq(1).show();
            }else if($(window).scrollTop() < $(".companyD .historyD .historyList").eq(3).offset().top && $(window).scrollTop() >= $(".companyD .historyD .historyList").eq(2).offset().top){
                $(".companyD .historyD .fixImg img").hide();
                $(".companyD .historyD .fixImg img").eq(2).show();
            }else if($(window).scrollTop() < $(".companyD .historyD .historyList").eq(4).offset().top && $(window).scrollTop() >= $(".companyD .historyD .historyList").eq(3).offset().top){
                $(".companyD .historyD .fixImg img").hide();
                $(".companyD .historyD .fixImg img").eq(3).show();
            }else if($(window).scrollTop() < $(".companyD .historyD .historyList").eq(5).offset().top && $(window).scrollTop() >= $(".companyD .historyD .historyList").eq(4).offset().top){
                $(".companyD .historyD .fixImg img").hide();
                $(".companyD .historyD .fixImg img").eq(4).show();
            }else{
                $(".companyD .historyD .fixImg img").hide();
                $(".companyD .historyD .fixImg img").eq(5).show();
            }
        }else{

        }

        //�룸같寃� 紐⑥뀡
        $(".companyD .historyD .historyList").each(function(q){
            if($(window).scrollTop() + $(window).height() <=  $(".companyD .historyD .historyList").eq(q).offset().top + 800){
                hisN = q;
                TweenMax.to($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(0), 1.1, {opacity:0, top:rePrevH[hisN][0], ease:Power3.easeOut});
                TweenMax.to($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(1), 1.1, {opacity:0, top:rePrevH[hisN][1], ease:Power3.easeOut});
                TweenMax.to($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(2), 1.1, {opacity:0, top:rePrevH[hisN][2], ease:Power3.easeOut});
            }else if($(window).scrollTop() + $(window).height() >  $(".companyD .historyD .historyList").eq(q).offset().top + 800 && $(window).scrollTop() + $(window).height() <=  $(".companyD .historyD .historyList").eq(q).offset().top + 900){
                hisN = q;
                TweenMax.to($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(0), 1.1, {opacity:1, top:rePrevH[hisN][0] - 30, ease:Power3.easeOut});
                TweenMax.to($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(1), 1.1, {opacity:1, top:rePrevH[hisN][1] - 70, ease:Power3.easeOut});
                TweenMax.to($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(2), 1.1, {opacity:1, top:rePrevH[hisN][2] - 50, ease:Power3.easeOut});
            }else if($(window).scrollTop() + $(window).height() >  $(".companyD .historyD .historyList").eq(q).offset().top + 900 && $(window).scrollTop() + $(window).height()<=  $(".companyD .historyD .historyList").eq(q).offset().top + 1000){
                hisN = q;
                TweenMax.to($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(0), 1.1, {opacity:1, top:rePrevH[hisN][0] - 60, ease:Power3.easeOut});
                TweenMax.to($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(1), 1.1, {opacity:1, top:rePrevH[hisN][1] - 140, ease:Power3.easeOut});
                TweenMax.to($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(2), 1.1, {opacity:1, top:rePrevH[hisN][2] - 100, ease:Power3.easeOut});
            }else if($(window).scrollTop() + $(window).height() >  $(".companyD .historyD .historyList").eq(q).offset().top + 1000 && $(window).scrollTop() + $(window).height()<=  $(".companyD .historyD .historyList").eq(q).offset().top + 1100){
                hisN = q;
                TweenMax.to($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(0), 1.1, {opacity:1, top:rePrevH[hisN][0] - 90, ease:Power3.easeOut});
                TweenMax.to($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(1), 1.1, {opacity:1, top:rePrevH[hisN][1] - 210, ease:Power3.easeOut});
                TweenMax.to($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(2), 1.1, {opacity:1, top:rePrevH[hisN][2] - 150, ease:Power3.easeOut});
            }else if($(window).scrollTop() + $(window).height() >  $(".companyD .historyD .historyList").eq(q).offset().top + 1100 && $(window).scrollTop() + $(window).height()<=  $(".companyD .historyD .historyList").eq(q).offset().top + 1200){
                hisN = q;
                TweenMax.to($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(0), 1.1, {opacity:1, top:rePrevH[hisN][0] - 120, ease:Power3.easeOut});
                TweenMax.to($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(1), 1.1, {opacity:1, top:rePrevH[hisN][1] - 280, ease:Power3.easeOut});
                TweenMax.to($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(2), 1.1, {opacity:1, top:rePrevH[hisN][2] - 200, ease:Power3.easeOut});
            }else if($(window).scrollTop() + $(window).height() >  $(".companyD .historyD .historyList").eq(q).offset().top + 1200 && $(window).scrollTop() + $(window).height()<=  $(".companyD .historyD .historyList").eq(q).offset().top + 1300){
                hisN = q;
                TweenMax.to($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(0), 1.1, {opacity:1, top:rePrevH[hisN][0] - 150, ease:Power3.easeOut});
                TweenMax.to($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(1), 1.1, {opacity:1, top:rePrevH[hisN][1] - 350, ease:Power3.easeOut});
                TweenMax.to($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(2), 1.1, {opacity:1, top:rePrevH[hisN][2] - 250, ease:Power3.easeOut});
            }else if($(window).scrollTop() + $(window).height() >  $(".companyD .historyD .historyList").eq(q).offset().top + 1300 && $(window).scrollTop() + $(window).height()<=  $(".companyD .historyD .historyList").eq(q).offset().top + 1400){
                hisN = q;
                TweenMax.to($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(0), 1.1, {opacity:0, top:rePrevH[hisN][0] - 180, ease:Power3.easeOut});
                TweenMax.to($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(1), 1.1, {opacity:0, top:rePrevH[hisN][1] - 420, ease:Power3.easeOut});
                TweenMax.to($(".companyD .historyD .historyList").eq(q).find(".bg img").eq(2), 1.1, {opacity:0, top:rePrevH[hisN][2] - 300, ease:Power3.easeOut});
            }
        });
    }

    //湲곗뾽�뚭컻_鍮꾩쟾
    if($(".visionD").length > 0){
        if($(window).scrollTop() + $(window).height() - 100 > $(".visionD .listDiv").offset().top){
            $(".visionD .listDiv .list").each(function(i){
                TweenMax.to($(".visionD .listDiv .list").eq(i), 0.9 , {top:0, opacity:1, delay:0.3*i + 0.2, ease:Power3.easeOut});
            });
        }
    }

    //�쒗뭹�뚭컻 �곸꽭
    if($(".productDiv .brandView").length > 0){
        if($(window).scrollTop() <= $(".productDiv .brandView .bannerD .banner").offset().top - 600){
            TweenMax.to($(".productDiv .brandView .bannerD .banner"), 1.2, {backgroundPosition:"50% 0%", ease:Power3.easeOut});
        }else if($(window).scrollTop() > $(".productDiv .brandView .bannerD .banner").offset().top - 600 && $(window).scrollTop() <= $(".productDiv .brandView .bannerD .banner").offset().top - 400){
            TweenMax.to($(".productDiv .brandView .bannerD .banner"), 1.2, {backgroundPosition:"50% 20%", ease:Power3.easeOut});
        }else if($(window).scrollTop() > $(".productDiv .brandView .bannerD .banner").offset().top - 400 && $(window).scrollTop() <= $(".productDiv .brandView .bannerD .banner").offset().top - 200){
            TweenMax.to($(".productDiv .brandView .bannerD .banner"), 1.2, {backgroundPosition:"50% 40%", ease:Power3.easeOut});
        }else if($(window).scrollTop() > $(".productDiv .brandView .bannerD .banner").offset().top - 200 && $(window).scrollTop() <= $(".productDiv .brandView .bannerD .banner").offset().top){
            TweenMax.to($(".productDiv .brandView .bannerD .banner"), 1.2, {backgroundPosition:"50% 60%", ease:Power3.easeOut});
        }else if($(window).scrollTop() > $(".productDiv .brandView .bannerD .banner").offset().top && $(window).scrollTop() <= $(".productDiv .brandView .bannerD .banner").offset().top + 200){
            TweenMax.to($(".productDiv .brandView .bannerD .banner"), 1.2, {backgroundPosition:"50% 80%", ease:Power3.easeOut});
        }else{
            TweenMax.to($(".productDiv .brandView .bannerD .banner"), 1.2, {backgroundPosition:"50% 100%", ease:Power3.easeOut});
        }
    }


    //�ы쉶怨듯뿄_媛쒖슂
    if($(".managementD .introD").length > 0){
        if($(window).width() > 741){
            if($(window).scrollTop() + $(window).height() - 100 > $(".managementD .listDiv").offset().top){
                $(".managementD .introD .listDiv .list").each(function(i){
                    TweenMax.to($(".managementD .introD .listDiv .list").eq(i), 0.9 , {top:0, opacity:1, delay:0.3*i + 0.2, ease:Power3.easeOut});
                });
            }
        }
    }
    //�ㅽ뵂�대끂踰좎씠��_�뚭컻
    if($(".introDiv").length > 0){
        $(".introDiv").each(function(){
            if($(window).scrollTop() <= $(this).offset().top - 600){
                TweenMax.to($(this), 1.2, {backgroundPosition:"50% 0%", ease:Power3.easeOut});
            }else if($(window).scrollTop() > $(this).offset().top - 600 && $(window).scrollTop() <= $(this).offset().top - 400){
                TweenMax.to($(this), 1.2, {backgroundPosition:"50% 20%", ease:Power3.easeOut});
            }else if($(window).scrollTop() > $(this).offset().top - 400 && $(window).scrollTop() <= $(this).offset().top - 200){
                TweenMax.to($(this), 1.2, {backgroundPosition:"50% 40%", ease:Power3.easeOut});
            }else if($(window).scrollTop() > $(this).offset().top - 200 && $(window).scrollTop() <= $(this).offset().top){
                TweenMax.to($(this), 1.2, {backgroundPosition:"50% 60%", ease:Power3.easeOut});
            }else if($(window).scrollTop() > $(this).offset().top && $(window).scrollTop() <= $(this).offset().top + 200){
                TweenMax.to($(this), 1.2, {backgroundPosition:"50% 80%", ease:Power3.easeOut});
            }else{
                TweenMax.to($(this), 1.2, {backgroundPosition:"50% 100%", ease:Power3.easeOut});
            }
        });
    }
}

/* 2019-01-23 異붽� */
function checkBroswer(){
    var agent = navigator.userAgent.toLowerCase(),
        name = navigator.appName;

    if(name === 'Microsoft Internet Explorer' || agent.indexOf('trident') > -1 || agent.indexOf('edge/') > -1) {
        browser = 'ie';
        if(name === 'Microsoft Internet Explorer') { // IE old version (IE 10 or Lower)
            agent = /msie ([0-9]{1,}[\.0-9]{0,})/.exec(agent);
            browser += parseInt(agent[1]);
        } else { // IE 11+
            if(agent.indexOf('trident') > -1) { // IE 11
                browser += 11;
            } else if(agent.indexOf('edge/') > -1) { // Edge
                browser = 'edge';
            }
        }
    } else if(agent.indexOf('safari') > -1) { // Chrome or Safari
        if(agent.indexOf('opr') > -1) { // Opera
            browser = 'opera';
        } else if(agent.indexOf('chrome') > -1) { // Chrome
            browser = 'chrome';
        } else { // Safari
            browser = 'safari';
        }
    } else if(agent.indexOf('firefox') > -1) { // Firefox
        browser = 'firefox';
    }
}
/* //2019-01-23 異붽� */

/* 2019-01-23 �섏젙 */
function headerScroll(){
    $(window).resize(function(){
        $(".header").addClass("up");
        if($(window).width() > 1263){
            if(browser.indexOf('ie') > -1){
                $("#wrap").on('mousewheel DOMMouseScroll', function(e) {
                    if(!isHeadFix){
                        var E = e.originalEvent;
                        delta = 0;
                        if (E.detail) {
                        }else{
                            delta = E.wheelDelta;
                            deltaY = E.deltaY
                            if(delta == 120){
                                $(".header").addClass("up");
                            }else if(delta == -120){
                                $(".header").removeClass("up");
                            }

                            /*
                            if(deltaY < 0){
                                $(".header").addClass("up");
                            }else{
                                $(".header").removeClass("up");
                            }*/
                        }
                    }
                })
            }else{
                var prev_scroll = $(window).scrollTop();
                var curtScrollY = $(window).scrollTop(), curtScrollYPlus = 0;
                $(window).scroll(function(){
                    if(!isHeadFix){
                        if(browser.indexOf('ie') < 0){
                            if($(window).scrollTop() > prev_scroll){
                                //�ㅽ겕濡ㅻ떎��
                                $(".header").removeClass("up");
                            }else{
                                //�ㅽ겕濡ㅼ뾽
                                $(".header").addClass("up");
                            }
                            prev_scroll = Math.abs($(window).scrollTop());
                        }
                    }
                });
            }

        }else{
            //�ㅽ겕濡ㅼ떆 �ㅻ뜑 愿���
            var prev_scroll = $(window).scrollTop();
            var curtScrollY = $(window).scrollTop(), curtScrollYPlus = 0;
            $(window).scroll(function(){
                if(!isHeadFix){
                    if($(window).scrollTop() > prev_scroll){
                        //�ㅽ겕濡ㅻ떎��
                        $(".header").removeClass("up");
                    }else{
                        //�ㅽ겕濡ㅼ뾽
                        $(".header").addClass("up");
                    }
                    prev_scroll = Math.abs($(window).scrollTop());
                }
            });
        }
    });$(window).resize();
}
/* //2019-01-23 �섏젙 */

function mobileThrD(){
    //紐⑤컮�� thrD
    $(".header #mGnb .dep .twoD li a").each(function(q){
        if($(this).parent("li").find(".thrD").length > 0){
            $(this).addClass("hasThr");
            $(this).attr("href" , "javascript:");
        }else{
            $(this).removeClass("hasThr");
        }
    });
}

function lnbWidth(){
    if(lnbWidthSum < $(window).width()){
        $(".lnbSwiper .swiper-wrapper").css('display', 'table');
    }else{
        $(".lnbSwiper .swiper-wrapper").css('display', 'flex');
    }
}

function tabSwiperWidth(){
    if(tabSwiperWidthSum < $(window).width()){
        $(".tabArea .swiper-wrapper").css('display', 'table');

    }else{
        $(".tabArea .swiper-wrapper").css('display', 'flex');
    }
}

function faqWidth(){
    if(faqWidthSum < $(window).width()){
        $(".faqSwiper .swiper-wrapper").css('display', 'table');
    }else{
        $(".faqSwiper .swiper-wrapper").css('display', 'flex');
    }
}

function swiperWidth(){
    if(swiperWidthSum < $(window).width()){
        $(".swiperArea .swiper-wrapper").css('display', 'table');
    }else{
        $(".swiperArea .swiper-wrapper").css('display', 'flex');
    }
}

function selWidth(){
    //$(window).resize(function(){
    if($(window).width() < selTabWSum + 130){
        $('.selectTab').addClass('mobile');
        $('.selectTab .tabBtn').css('display','none');
    }else{
        $('.selectTab').removeClass('mobile');
        // $('.selectTab > div .selectScroll a').click(function(e){
        // 	e.preventDefault();
        // 	e.stopPropagation();
        // });
        $('.selectTab .tabBtn').css('display','block');
    }
    //});
}

function familyD(){
    //footer �⑤�由ъ궗�댄듃
    var isFamilyMove = false;
    var openH = 0;
    var closeH = 0;
    $(window).resize(function(){
        if($(window).width() > 964){
            if($("#footer .etcD .familyD .twoD a").length > 10){
                openH = 420;
                $("#footer .etcD .familyD .twoD").height("360px");
            }else{
                openH = $("#footer .etcD .familyD .twoD > a").length * 36  + 60;
                $("#footer .etcD .familyD .twoD").height($("#footer .etcD .familyD .twoD a").length * 36 );
            }
            closeH = "40px";
            if(!$("#footer .etcD .familyD").hasClass("on")){
                $("#footer .etcD .familyD").css("height",closeH)
            }else{
                $("#footer .etcD .familyD").css("height",openH)
            }
        }else{
            if($("#footer .etcD .familyD .twoD a").length > 10){
                openH = 295;
                $("#footer .etcD .familyD .twoD").height("250px");
            }else{
                openH = $("#footer .etcD .familyD .twoD a").length * 25  + 45;
                $("#footer .etcD .familyD .twoD").height($("#footer .etcD .familyD .twoD a").length * 25 );
            }
            closeH = "30px";
            if(!$("#footer .etcD .familyD").hasClass("on")){
                $("#footer .etcD .familyD").css("height",closeH)
            }else{
                $("#footer .etcD .familyD").css("height",openH)
            }
        }
    });$(window).resize();
    $("#footer .etcD .familyD > a").click(function(){
        if(!isFamilyMove){
            isFamilyMove = true;
            if(!$(this).parent(".familyD").hasClass("on")){
                $(this).parent(".familyD").addClass("on");
                $(this).parent(".familyD").find(".twoD").stop(true, true).slideDown(300, function(){
                    $(this).parent(".familyD").find(".twoD").mCustomScrollbar({
                        scrollInertia:50,
                        advanced:{
                            updateOnContentResize: true
                        }
                    });
                });

                $(this).parent(".familyD").animate({height:openH}, 300, function(){
                    isFamilyMove = false;
                });
            }else{
                $(this).parent(".familyD").removeClass("on");
                $(this).parent(".familyD").find(".twoD").stop(true, true).slideUp(300);
                $(this).parent(".familyD").find(".twoD").mCustomScrollbar("destroy");
                $(this).parent(".familyD").animate({height:closeH}, 300, function(){
                    isFamilyMove = false;
                });
            }
        }
    });

    $("#footer .etcD .familyD .twoD > a").click(function(){
        var siteText = $(this).text();
        $("#footer .etcD .familyD > a").text(siteText);
        $(this).parents(".familyD").removeClass("on");
        $(this).parents(".familyD").find(".twoD").stop(true, true).slideUp(300);
        $(this).parents(".familyD").find(".twoD").mCustomScrollbar("destroy");
        $(this).parents(".familyD").animate({height:closeH}, 300, function(){
            isFamilyMove = false;
        });
    });
}                                                                                                                                                      
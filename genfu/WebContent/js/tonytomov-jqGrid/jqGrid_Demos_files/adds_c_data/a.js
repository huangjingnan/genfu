﻿var _qevents;if(typeof DMAds=="undefined"){var SendSearchTermsToServer=window.location.protocol==="http:",OnlySendForCodeProject=!1,CodeProjectPublisherId="lqm.codeproject.site",QuantScriptRequired=!0,adServer=adServer||window.location.protocol+"//ads.DeveloperMedia.com/",SearchTermUrl=window.location.protocol+"//apps.developermedia.com/Ads/PageTerms/GetTerms",DMAds={GetQueryTerms:function(){function u(n){for(var u="",i,t=0;t<r.length;t++)if(i=r[t],n.indexOf(i.d)>=0){u=i.q;break}return u}function f(n,t){var r=t.toLowerCase().indexOf(n),u,i;return r<0||r+n.length>=t.length?"":(u=t.indexOf("&",r),u<0&&(u=t.length),i=t.substring(r+n.length,u),i=i.replace(/\+/gi," "),i=decodeURIComponent(i),i.replace(/\"/gi,""))}function e(n){if(n===undefined)return"";var t=n.replace(/^\s+|\s+$/gi,"");return t?(t=t.replace(/\bAND\b|\bNOT\b|^NOT\b|\bOR\b|[^A-Z0-9\+\-\#\._\s]+|\b[A-Z0-9]+:/gi," "),t.replace(/\s+/g," ")):""}var r=[{d:"www.google.",q:"q="},{d:"www.bing.com",q:"q="},{d:"search.live.com",q:"q="},{d:"search.yahoo.com",q:"p="},{d:"codeproject.com",q:"q="},{d:"msdn.microsoft.com",q:"query="},{d:"www.ask.com",q:"q="},{d:"yandex.com",q:"text="},{d:"yandex.ru",q:"text="},{d:"www.baidu.com",q:"wd="},{d:"localhost",q:"q="}],n=document.URL,i="",t=u(n);if(t!==""&&(i=e(f(t,n))),i==""){if(n=document.referrer.toLocaleLowerCase(),!n)return"";t=u(n);t!=""&&(i=e(f(t,n)))}return i},GetRandom:function(n,t){for(var u,i="",r=0;r<n;r++)u=Math.floor(Math.random()*t).toString(t).toUpperCase(),i=i+u;return i},PageRandomNumber:null,PageSearchTerms:null,Tile:1,CurrentDocument:null,BuildIFrameTag:function(n){var t='<iframe id="dmad{tile}" allowtransparency="false" style="z-index:10" ';return t=n&&n.width&&n.width>1?t+'width="{width}" ':t+'width="100%" ',t=n&&n.height&&n.height>0?t+'height="{height}" ':t+'height="0" ',t=t+'marginwidth="0" marginheight="0" frameborder="0" scrolling="no"><\/iframe>',this.ReplacePlaceholders(t,n)},BuildJavaScriptTag:function(n){var t='<script language="JavaScript" src="'+window.location.protocol+'//ad.doubleclick.net/N6839/adj/{sitename}/{zonename};{searchterm}sz={format};{type}tile={tile};ord={timestamp}?" type="text/javascript"><\/script>';return this.ReplacePlaceholders(t,n)},ReplacePlaceholders:function(n,t){return n=n.replace(/\{sitename\}/g,t.sitename),n=n.replace(/\{zonename\}/g,t.zonename),n=t.tags?n.replace(/\{searchterm\}/g,"kw="+encodeURIComponent(this.EscapeSpecialCharacters(t))+";"):n.replace(/\{searchterm\}/g,""),n=n.replace(/\{tile\}/g,t.tile.toString()),n=n.replace(/\{format\}/g,t.format),n=n.replace(/\{width\}/g,t.width),n=n.replace(/\{height\}/g,t.height),n=n.replace(/\{target\}/g,t.target),n=n.replace(/\{timestamp\}/g,this.PageRandomNumber),t.type?n.replace(/\{type\}/g,"type="+encodeURIComponent(t.type)+";"):n.replace(/\{type\}/g,"")},EscapeSpecialCharacters:function(n){var t=n.tags,i;t=t.replace(/\+/gi,"{plus}");t=t.replace(/\#/gi,"{sharp}");t=t.replace(/\./gi,"{dot}");t=t.replace(/[\#\*\.\(\)\+\<\>\[\]]/gi,"");for(var r=t.split(","),u=[];r.length>0;)i=r.shift(),/[^\u0020-\u007f]/.test(i)||u.push(i);return u.join(",")},tagInfo:[{id:1,n:"Standard Banner",h:60,w:468},{id:2,n:"Product Spotlight",h:2,w:1},{id:3,n:"Hosting Spotlight",h:2,w:1},{id:4,n:"Skyscraper",h:600,w:120},{id:5,n:"Square",h:125,w:125},{id:6,n:"Medium Rectangle",h:250,w:300},{id:7,n:"Large Rectangle",h:280,w:336},{id:8,n:"Leaderboard",h:90,w:728},{id:9,n:"HTML Ad",h:0,w:0},{id:10,n:"Fixed Square",h:125,w:125},{id:11,n:"Fixed Banner",h:60,w:468},{id:12,n:"Half Skyscraper",h:300,w:120},{id:13,n:"IAB Button",h:90,w:120},{id:14,n:"Rectangle",h:120,w:150},{id:15,n:"Thin Horizontal",h:27,w:408},{id:16,n:"Button",h:30,w:100},{id:17,n:"DogEar",h:0,w:0},{id:18,n:"Wide Skyscraper",h:600,w:160},{id:19,n:"Tracking Only",h:1,w:1},{id:20,n:"Mixed 120x90-Text",h:5,w:1},{id:21,n:"Home page top left (150 X 80)",h:80,w:150},{id:22,n:"SponsorEmail",h:0,w:0},{id:23,n:"Email",h:60,w:60},{id:24,n:"TextLinks",h:0,w:0},{id:25,n:"Zone",h:0,w:0},{id:26,n:"Goal group",h:0,w:0},{id:27,n:"Article",h:0,w:0},{id:28,n:"Search Sponsor Box",h:30,w:120},{id:29,n:"Microbar",h:31,w:88},{id:30,n:"Sponsor Link",h:1,w:0}],DetermineTagSize:function(n){var i,u,r,t;if(n.format)if(isNaN(n.format))i=n.format.split("x"),i.length==2&&(isFinite(i[0])&&(n.width=i[0]),isFinite(i[1])&&(n.height=i[1]));else for(u=!1,r=0;r<this.tagInfo.length&&!u;)t=this.tagInfo[r],t.id==n.format&&(t.w!=0&&(n.width=t.w),t.h!=0&&(n.height=t.h),n.type=t.name,u=!0,n.format=""+t.w+"x"+t.h),r++},MapDmIdsToDart:function(n){var t="lqm.",i=".site";n.publisher?(n.sitename=isNaN(n.publisher)?n.publisher:t+"pub"+n.publisher+i,this.MapDmZoneToDartZone(n)):n.site&&(n.sitename=t+"codeplex"+i,n.zonename=n.charity?"donated2charity":n.site.toLowerCase())},zoneInfo:[{id:1,n:"ron"},{id:51,n:"it"},{id:52,n:"designer"},{id:2,n:"above_the_fold"},{id:9,n:"wpf"},{id:14,n:"silverlight"},{id:3,n:"reportingservices"},{id:4,n:"sql"},{id:5,n:"whitepaper"},{id:6,n:"featuredwhitepaper"},{id:7,n:"crystalreports"},{id:10,n:"vs2005video"},{id:11,n:"ros_dogear"},{id:12,n:"homepage_dogear"},{id:13,n:"excludehomepage_dogear"},{id:15,n:"lqm_dogear"},{id:17,n:"mvc"},{id:18,n:"ajax"},{id:38,n:"devexpress_video"},{id:39,n:"devmavens_sidebar"},{id:40,n:"devmavens_offer"},{id:44,n:"silverlight"},{id:45,n:"wpf"},{id:54,n:"csharp_articles"}],MapDmZoneToDartZone:function(n){if(n.zone){for(var i=!1,t=0;t<this.zoneInfo.length&&!i;)this.zoneInfo[t].id==n.zone&&(n.zonename=this.zoneInfo[t].n,i=!0),t++;i||(n.zonename=isNaN(n.zone)?n.zone.toLowerCase():"ron")}else n.zonename="ron"},GetDocHeight:function(n){return n.height||n.body&&n.body.scrollHeight},HideRefs:function(n,t,i){var e=this,u,f,o,r;for(i.format.indexOf("1x")===0?(t.innerHTML=n.body.innerHTML,u=t):u=n,f=u.getElementsByTagName("a"),o=function(n){var t=n.href,i,f=t.indexOf("&adurl="),r,u;if(i=f>0?decodeURIComponent(t.substring(f+7)):adServer+e.GetRandom(4,16)+"-"+e.GetRandom(7,16),n.href=i,r=function(){n.href=t},u=function(){n.href=i},n.addEventListener)n.addEventListener("mousedown",r,!1),n.addEventListener("mouseover",u,!1);else try{n.attachEvent("onmousedown",r);n.attachEvent("onmouseover",u)}catch(o){}},r=0;r<f.length;r++)o(f[r])}};if(typeof DMAds.CreateAds!="function"&&(DMAds.CreateAds=function(){var t=this,v=1e3,r,n,e;this.PageRandomNumber=this.GetRandom(32,16);var y=function(n,t,i,r){var o=[],u,e;i==null&&(i=document);r==null&&(r="*");var f=i.getElementsByTagName(r),s=f.length,h=new RegExp("(^|\\s)"+t+"(\\s|$)");for(u=0,e=0;u<s;u++)h.test(f[u].getAttribute(n))&&(o[e]=f[u],e++);return o},p=function(n,t,i){var e=[],r,f;t==null&&(t=document);i==null&&(i="*");var u=t.getElementsByTagName(i),o=u.length,s=new RegExp("(^|\\s)"+n+"(\\s|$)");for(r=0,f=0;r<o;r++)s.test(u[r].className)&&(e[f]=u[r],f++);return e},w=function(n,i,r,u){var e=50,f=0,o=window.setInterval(function(){var r=t.GetDocHeight(n);r>0&&((--e==0||r===f)&&(window.clearInterval(o),t.HideRefs(n,u,i)),f=r)},100)},b=function(n){setTimeout(function(){n&&n.body&&top.postMessage(n.body.innerHTML?"DM-enabled":"DM-disabled","*")},1e3)},h=function(i){for(var h=n[i],u={height:0,width:0,publisher:undefined,zone:undefined,site:undefined,tags:undefined,sitename:undefined,zonename:undefined,target:undefined,format:undefined,tile:undefined,type:undefined},y=h.attributes,k=y.length,o={},s,l,f,e,a,v,p,c=0;c<k;c++)s=y.item(c),s.nodeName.indexOf("lqm_")==0&&(l=s.nodeName.slice(4),o[l]=s.nodeValue),s.nodeName.indexOf("data-")==0&&(l=s.nodeName.slice(5),o[l]=s.nodeValue);if(u.publisher=o.publisher,u.zone=o.zone,u.site=o.site,u.tags=o.tags,u.format=o.format,u.tile=i+1,u.target="_blank",u.tags&&(u.tags=decodeURIComponent(u.tags)),r&&r!=""&&(u.tags=u.tags?u.tags+","+r:r),t.DetermineTagSize(u),t.MapDmIdsToDart(u),h.innerHTML=t.BuildIFrameTag(u),f=h.getElementsByTagName("iframe")[0],f.onerror=function(){return!0},e=f.contentDocument||f.contentWindow.document||f.contentWindow.window.document,a=function(){u.height<=1&&(this.height=t.GetDocHeight(e));t.HideRefs(e,h,u)},f.addEventListener)f.addEventListener("load",a,!1);else try{f.attachEvent("onload",a)}catch(d){}v=navigator.userAgent&&navigator.userAgent.indexOf("MSIE")>=0;p=navigator.userAgent&&navigator.userAgent.indexOf("Opera")>=0;e.write(t.BuildJavaScriptTag(u));v||p||!e.close||e.close();v&&w(e,u,f,h);b(e)},k=function(n){var r=n.getBoundingClientRect(),i=0,t=0;return typeof window.innerWidth=="number"?(i=window.innerWidth,t=window.innerHeight):document.documentElement&&(document.documentElement.clientWidth||document.documentElement.clientHeight)?(i=document.documentElement.clientWidth,t=document.documentElement.clientHeight):document.body&&(document.body.clientWidth||document.body.clientHeight)&&(i=document.body.clientWidth,t=document.body.clientHeight),r.top<=t&&r.bottom>=0},o=function(){for(var t=0;t<n.length;t++)f[t]===!1&&k(n[t])&&(f[t]=!0,h(t)),f[t]===!0&&(u[t]||(u[t]=c(t)),a(t))},d=function(n,t,i){var s,u=window.XMLHttpRequest?new XMLHttpRequest:new ActiveXObject("MSXML2.XMLHTTP"),f,o,h,e;if(!u)return i(t===CodeProjectPublisherId);f=document.URL;f.indexOf("?")>0&&(f=f.substring(0,f.indexOf("?")));try{o=top.document.title}catch(c){o="FAILED TO GET DOCUMENT TITLE"}h='{"terms":"'+n+'","title":"'+o+'","url":"'+f+'","publisher":"'+t+'"}';s=setTimeout(function(){u.abort()},v);e=function(responseText){if(clearTimeout(s),responseText){var returnedObject=eval("("+responseText+")");r=returnedObject&&returnedObject.terms?n+","+returnedObject.terms:n}i(t===CodeProjectPublisherId)};u.onreadystatechange=function(){try{u.readyState==4&&(u.status==200?e(u.responseText):e(null))}catch(n){e(null)}};u.open("POST",SearchTermUrl,!0);u.setRequestHeader("Content-Type","application/json");u.send(h)},s=function(t){for(var u,r,i=0;i<n.length;i++)f[i]=!1,u=t,r=n[i].getAttribute("data-display")||n[i].getAttribute("lqm_loadOnView"),r==="onscroll"||r==="true"?u=!0:(r==="always"||r==="false")&&(u=!1),u||(h(i),f[i]=!0);if(o(),window.addEventListener)window.addEventListener("resize",l,!1),window.addEventListener("scroll",o,!1);else try{window.attachEvent("onresize",l);window.attachEvent("onscroll",o)}catch(e){}g(QuantScriptRequired)},g=function(n){var t,i;n&&(t=document.createElement("script"),t.src=(document.location.protocol=="https:"?"https://secure":"http://edge")+".quantserve.com/quant.js",t.async=!0,t.type="text/javascript",i=document.getElementsByTagName("script")[0],i.parentNode.insertBefore(t,i))},c=function(t){if(n[t].children[0]){var r=nt(n[t].children[0]),i={};return(i.isStickyRequired=n[t].getAttribute("data-sticky")&&n[t].getAttribute("data-sticky").toLowerCase()==="top",i.absoluteTop=r.y,i.absoluteLeft=r.x,i.absoluteTop===-1)?null:(i.originalPosition=n[t].style.position,i)}},l=function(){for(i=0;i<u.length;i++)u[i]&&(n[i].children[0].style.position=u[i].originalPosition,u[i]=c(i),a(i))},a=function(t){var i=u[t];i&&i.isStickyRequired&&window.pageYOffset+10>=i.absoluteTop?(n[t].children[0].style.position="fixed",n[t].children[0].style.top="10px",n[t].children[0].style.left=i.absoluteLeft-window.pageXOffset+"px",n[t].clientHeight===0&&(n[t].style.height=n[t].children[0].clientHeight+"px"),n[t].clientWidth===0&&(n[t].style.width=n[t].children[0].clientWidth+"px")):i&&i.isStickyRequired&&(n[t].children[0].style.position=i.originalPosition)},nt=function(n){var t={};if(t.x=-1,t.y=-1,n.getBoundingClientRect){var r=n.getBoundingClientRect(),i=document.documentElement,u=window.pageYOffset||i.scrollTop||document.body.scrollTop||0,f=window.pageXOffset||i.scrollLeft||document.body.scrollLeft||0,e=i.clientTop||document.body.clientTop||0,o=i.clientLeft||document.body.clientLeft||0;t.y=r.top+u-e;t.x=r.left+f-o}return t},f=[],u=[];if(this.PageSearchTerms==null&&(this.PageSearchTerms=this.GetQueryTerms()),r=this.PageSearchTerms,n=p("lqm_ad",document,"div"),(n==null||n.length<=0)&&(n=y("data-type","ad",document,"div")),n!=null&&n.length>0)if(e=n[0].getAttribute("lqm_publisher")||n[0].getAttribute("data-publisher"),SendSearchTermsToServer&&(!OnlySendForCodeProject||e===CodeProjectPublisherId))try{d(r,e,s)}catch(tt){s(e===CodeProjectPublisherId)}else s(e===CodeProjectPublisherId)}),document.readyState==="complete")DMAds.CreateAds();else if(window.addEventListener)window.addEventListener("load",function(){DMAds.CreateAds()},!1);else try{window.attachEvent("onload",function(){DMAds.CreateAds()})}catch(e){}_qevents=_qevents||[];_qevents.push({qacct:"p-g6uZkrDA2nB2y"})}
//# sourceMappingURL=a.min.js.map
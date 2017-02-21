(function () {
    var xhr = new XMLHttpRequest()
    xhr.open('GET', ctx + '/luck/getImgData?imgUrl=' + imageUrl)
    xhr.onload = function () {
        var img = new Image();
        img.setAttribute('src', xhr.responseText)
        img.addEventListener('load', function () {
            var canvas = document.createElement('canvas')
            canvas.width = img.naturalWidth;
            canvas.height = img.naturalHeight;
            var context = canvas.getContext('2d')
            context.drawImage(img, 0, 0)
            document.querySelector('#cutImage').style.backgroundImage = 'url(' + canvas.toDataURL() + ')'
            canvas = null;
            img = null;
        })
    }
    xhr.send()
    var lotteryTip = document.querySelector('#lotteryTip')
    if(currentTime > activityEndTime){
        lotteryTip.innerHTML = '奖品已经抢完成,活动结束';
    }
    if(currentTime < activityStartTime){
        var gapTime = activityStartTime - currentTime;
        var day = parseInt(gapTime/1000/60/60/24);
        if(day > 0){
            lotteryTip.innerHTML = '倒计时<span>'+day+'</span>天'
        }else{
            var timeInterval = setInterval(function () {
                if(gapTime <= 0){
                    clearInterval(timeInterval)
                    lotteryTip.innerHTML = '摇一摇，有惊喜';
                    if (window.DeviceMotionEvent) {
                        var speed = 25;
                        var x = y = z = lastX = lastY = lastZ = 0;
                        window.addEventListener('devicemotion', devicemotion, false)
                        function devicemotion() {
                            var acceleration = event.accelerationIncludingGravity;
                            x = acceleration.x;
                            y = acceleration.y;
                            if (Math.abs(x - lastX) > speed || Math.abs(y - lastY) > speed) {
                                document.querySelector('#shake').classList.add('animate')
                                lottery()
                            }
                            lastX = x;
                            lastY = y;
                        }
                    }
                    return;
                }
                var hours = parseInt(gapTime/1000/60/60);
                if(hours < 10){
                    hours = '0'+hours;
                }
                var minutes = parseInt(gapTime/1000/60%60);
                if(minutes < 10){
                    minutes = '0'+minutes;
                }
                var seconds = parseInt(gapTime/1000%60);
                if(seconds < 10){
                    seconds = '0'+seconds;
                }
                lotteryTip.innerHTML = '倒计时<span>'+hours +':'+ minutes + ':' + seconds +'</span>';
                gapTime -= 1000;
            }, 1000)
        }
    }
    var container = document.querySelector('#container')
    var page1 = document.querySelector('#page1')
    var page2 = document.querySelector('#page2')
    var page3 = document.querySelector('#page3')
    page1.style.minHeight = window.innerHeight + 'px';
    page2.style.minHeight = window.innerHeight + 'px';
    page3.style.minHeight = window.innerHeight + 'px';
    var times = document.querySelectorAll('.create-time')
    for (var i = 0, j = times.length; i < j; i++) {
        var time = times[i].getAttribute('data-time');
        times[i].innerHTML = getTime(time)
    }
    function getTime(time) {
        var date = new Date(parseFloat(time))
        var month = date.getMonth() + 1;
        if (month < 10) {
            month = '0' + month;
        }
        var day = date.getDate()
        if (day < 10) {
            day = '0' + day;
        }
        var hours = date.getHours()
        if (hours < 10) {
            hours = '0' + hours;
        }
        var minutes = date.getMinutes()
        if (minutes < 10) {
            minutes = '0' + minutes;
        }
        return month + '-' + day + ' ' + hours + ':' + minutes;
    }

    document.querySelector('#playBtn').addEventListener('click', function () {
        if(isLottery == 'true'){
            page3.style.display = 'block';
            page1.style.transform = 'translateY(-100%)';
            page1.style.webkitTransform = 'translateY(-100%)';
            page3.style.transform = 'translateY(-' + page1.clientHeight + 'px)';
            page3.style.webkitTransform = 'translateY(-' + page1.clientHeight + 'px)';
            return;
        }
        page2.style.display = 'block';
        page1.style.transform = 'translateY(-100%)';
        page1.style.webkitTransform = 'translateY(-100%)';
        page2.style.transform = 'translateY(-' + page1.clientHeight + 'px)';
        page2.style.webkitTransform = 'translateY(-' + page1.clientHeight + 'px)';
        if(currentTime <= activityEndTime && currentTime >= activityStartTime ) {
            if (window.DeviceMotionEvent) {
                var speed = 25;
                var x = y = z = lastX = lastY = lastZ = 0;
                window.addEventListener('devicemotion', devicemotion, false)
                function devicemotion() {
                    var acceleration = event.accelerationIncludingGravity;
                    x = acceleration.x;
                    y = acceleration.y;
                    if (Math.abs(x - lastX) > speed || Math.abs(y - lastY) > speed) {
                        document.querySelector('#shake').classList.add('animate')
                        lottery()
                    }
                    lastX = x;
                    lastY = y;
                }
            } else {
                setTimeout(function () {
                    lottery()
                }, 3000)
            }
        }
    })

    var isLotterying = false;
    function lottery(){
        if(isLotterying){
            return;
        }
        var xhr = new XMLHttpRequest()
        xhr.open('POST', ctx + '/luck/lottery?id=' + userId)
        xhr.onload = function () {
            var result = JSON.parse(xhr.responseText)
            if (result.data.luck.id == 5) {
                var prizeTitle = '奖品你好,奖品再见......';
                document.querySelector('#prizeTitle').innerHTML = prizeTitle;
            } else {
                var prizeTitle = '恭喜您抽到一张';
                switch (result.data.luck.id) {
                    case 1:
                        prizeTitle = '恭喜您抽到一件';
                        break;
                    case 2:
                        prizeTitle = '恭喜您抽到一张';
                        break;
                    case 3:
                        prizeTitle = '恭喜您抽到一个';
                        break;
                    case 4:
                        prizeTitle = '恭喜您抽到一张';
                        break;
                }
                document.querySelector('#prizeTitle').innerHTML = prizeTitle;
                document.querySelector('#prizeName').innerHTML = result.data.luck.name;
            }
            setTimeout(function () {
                document.querySelector('#shake').classList.remove('animate')
                page3.style.display = 'block';
                page2.style.transform = 'translateY(-' + (page2.clientHeight + page1.clientHeight) + 'px)';
                page2.style.webkitTransform = 'translateY(-' + (page2.clientHeight + page1.clientHeight) + 'px)';
                page3.style.transform = 'translateY(-' + (page2.clientHeight + page1.clientHeight) + 'px)';
                page3.style.webkitTransform = 'translateY(-' + (page2.clientHeight + page1.clientHeight) + 'px)';
            }, 2000)
        }
        isLotterying = true;
        xhr.send();
    }
}).apply(window)

function share(data) {
    wx.onMenuShareTimeline({
        title: data.desc, // 分享标题
        link: data.link, // 分享链接
        imgUrl: data.imgUrl, // 分享图标
        success: function () {

        },
        cancel: function () {

        }
    });
    //分享到微信联系人
    wx.onMenuShareAppMessage({
        title: data.title, // 分享标题
        desc: data.desc, // 分享描述
        link: data.link, // 分享链接
        imgUrl: data.imgUrl, // 分享图标
        type: 'link', // 分享类型,music、video或link，不填默认为link
        dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
        success: function () {
            // 用户确认分享后执行的回调函数
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
        }
    });
    //分享到QQ
    wx.onMenuShareQQ({
        title: data.title, // 分享标题
        desc: data.desc, // 分享描述
        link: data.link, // 分享链接
        imgUrl: data.imgUrl, // 分享图标
        success: function () {
            // 用户确认分享后执行的回调函数
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
        }
    });
}
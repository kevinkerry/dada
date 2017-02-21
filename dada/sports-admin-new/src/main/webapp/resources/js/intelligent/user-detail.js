(function () {
    var width = window.innerWidth * 0.86;
    var circulate = document.querySelector('.m-circulate')
    var bgImg = document.querySelector('.g-bg')
    circulate.style.height = window.innerWidth * 0.86 + 'px';
    var content = document.querySelector('#content')
    if (content.clientHeight < window.innerHeight) {
        content.style.height = window.innerHeight + 'px'
    }
    bgImg.style.height = content.clientHeight + 'px';
    var btnMore = document.querySelector('.btn-more')
    var bMore = document.querySelector('.m-more')
    btnMore && btnMore.addEventListener('click', function () {
        if (bMore.classList.contains('active')) {
            bMore.classList.remove('active')
        } else {
            bMore.classList.add('active')
            bMore.classList.add('fadeInDown')
        }
        bgImg.style.height = content.clientHeight + 'px';
    })
    btnMore && bMore.addEventListener('webkitAnimationEnd', function () {
        this.classList.remove('fadeInDown')
    })
    var start = {}, delta = {}, index = 0, currentImg, starting = false, animate,
        direction = 'right';
    var imgs = document.querySelectorAll('.m-circulate .img')
    var totalCount = imgs.length;
    for (var i = 0; i < totalCount; i++) {
        var img = imgs[i];
        img.style.zIndex = (totalCount - i) * 10;
        var lazySrc = img.getAttribute('lazy-src')
        var tempArr = lazySrc.split('!')
        if (tempArr.length > 1) {
            var imgType = tempArr[1].split('.')[1]
            img.style.backgroundImage = 'url(' + tempArr[0] + '!' + parseInt(width) + 'x' + parseInt(width) + '.' + imgType + ')';
        } else {
            img.style.backgroundImage = 'url(' + tempArr[0] + ')'
        }
        img.style.width = width + 'px';
        img.style.height = width + 'px';
        img.addEventListener('touchstart', function (e) {
            var touches = e.touches[0];
            start = {
                x: touches.pageX,
                y: touches.pageY,
                time: +new Date()
            };
        })
        img.addEventListener('touchmove', function (e) {
            if (e.touches.length > 1 || e.scale && e.scale !== 1) {
                return
            }
            e.preventDefault();
            var touches = e.touches[0];
            delta = {
                x: touches.pageX - start.x,
                y: touches.pageY - start.y
            }
        })
        img.addEventListener('touchend', function (e) {
            if (delta.x && Math.abs(delta.x) > 30 && !starting) {
                starting = true;
                if (delta.x < 0) {
                    animate = 'zoomOutLeft';
                } else {
                    animate = 'zoomOutRight';
                }
                currentImg = e.target;
                next()
            } else if (delta.y < -30) {
                // document.body.scrollTop = Math.abs(delta.y)
            } else if (delta.y > 30) {
                //document.body.scrollTop = document.body.scrollTop - Math.abs(delta.y)
            }
        })
    }

    function next() {
        imgs[index].style.opacity = 1;
        currentImg.classList.add(animate)
        function end() {
            if (delta.x < 0) {
                if (direction == 'right') {
                    var tempArr = [];
                    for (var i = index; i >= 0; i--) {
                        tempArr.push(imgs[i])
                    }
                    for (var i = totalCount-1; i > index; i--) {
                        tempArr.push(imgs[i])
                    }
                    for (var i = 0, j = tempArr.length; i < j; i++) {
                        tempArr[i].style.zIndex = (totalCount - i) * 10;
                    }
                    direction = 'left'
                }
                for (var i = 0; i < totalCount; i++) {
                    if (i != index) {
                        imgs[i].style.zIndex = parseInt(imgs[i].style.zIndex) + 10;
                    } else {
                        imgs[i].style.zIndex = 10;
                    }
                }
                index--;
            } else {
                if (direction == 'left') {
                    var tempArr = [];
                    for (var i = index; i < totalCount; i++) {
                        tempArr.push(imgs[i])
                    }
                    for (var i = 0; i < index; i++) {
                        tempArr.push(imgs[i])
                    }
                    for (var i = 0, j = tempArr.length; i < j; i++) {
                        tempArr[i].style.zIndex = (totalCount - i) * 10;
                    }
                    direction = 'right'
                }
                for (var i = 0; i < totalCount; i++) {
                    if (i != index) {
                        imgs[i].style.zIndex = parseInt(imgs[i].style.zIndex) + 10;
                    } else {
                        imgs[i].style.zIndex = 10;
                    }
                }
                index++;
            }
            if (index < 0) {
                index = totalCount - 1;
            }
            if (index >= totalCount) {
                index = 0;
            }
            currentImg.removeEventListener('webkitAnimationEnd', end, false)
            currentImg.classList.remove(animate)
            starting = false;
        }

        currentImg.addEventListener('webkitAnimationEnd', end, false)
    }
})()
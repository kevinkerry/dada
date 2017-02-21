(function(){
    var currentPage = 1, totalPage,  totalHeight1 = 0, totalHeight2 = 0,
        userList1 = document.querySelector('#userList1'),
        userList2 = document.querySelector('#userList2'),
        allImgs = [], currentScrollTop = 0,
        searchInput = document.querySelector('#searchInput'),
        loading = document.querySelector('#loading'),
        isSearch = false;
    document.querySelector('#addBtn').addEventListener('click', function () {
        location.href = ctx + '/adminuser/add';
    })
    document.querySelector('#btnSearch').addEventListener('click', function () {
        if(searchInput.value.length == 0){
            swal('请输入要搜索的ID或名字')
            return
        }
        currentPage = 1;
        userList1.innerHTML = '';
        userList2.innerHTML = '';
        totalHeight1 = 0;
        totalHeight2 = 0;
        isSearch = true;
        loadData(searchInput.value)
    })
    loadData()
    function loadData(searchValue){
        if(totalPage && currentPage > totalPage){
            return;
        }
        loading.style.opacity = 1;
        var url = ctx +'/adminuser/queryPage?currentPage=' + currentPage++;
        if(searchValue){
            url += '&keyword=' + searchValue;
        }
        var xhr = new XMLHttpRequest()
        xhr.open('GET', url)
        xhr.onload = function () {
            var result = JSON.parse(xhr.responseText)
            var users = result.result;
            totalPage = result.totalPages;
            renderData(users)
            loading.style.opacity = 0;
        }
        xhr.send()
    }

    window.addEventListener('scroll', function () {
        if(document.body.scrollTop - currentScrollTop > 10){
            checkImgLoad()
        }
        currentScrollTop = document.body.scrollTop;
    })
    function renderData(users){
        for(var i= 0,j=users.length; i<j; i++){
            var user = users[i];
            var imgUrl = getImgUrl(user)
            var htmlTemple = [];
            htmlTemple.push('<img class="user-img"')
            if(user.height){
                htmlTemple.push(' style="height:"'+user.height+'px;"')
            }
            htmlTemple.push(' src="'+ctx+'/resources/images/loading.gif" lazy-src="http://image.dadasports.cn'+ imgUrl +'" alt="哒人图片">')
            htmlTemple.push('<div class="user-info"><span class="user-id">ID:'+ user.id +'</span>'+ user.username +'</div>')
            var li = document.createElement('li');
            li.classList.add('item')
            li.setAttribute('data-id', user.id)
            li.innerHTML = htmlTemple.join('')
            li.addEventListener('click', function () {
                location.href = ctx + '/adminuser/'+this.getAttribute('data-id')+'/detail'
            })
            if(totalHeight1 <= totalHeight2){
                userList1.appendChild(li)
                totalHeight1 += user.newHeight + 30;
            }else{
                userList2.appendChild(li)
                totalHeight2 += user.newHeight + 30;
            }
        }
        allImgs = document.querySelectorAll('.u-user-container .user-img');
        allImgs[allImgs.length-1].addEventListener('load', function () {
            if(this.getAttribute('loaded')){
                loadData(isSearch ? searchInput.value : null)
            }
        })
        checkImgLoad();
    }

    function checkImgLoad(){
        var height = window.innerHeight + document.body.scrollTop;
        for(var i= 0, j=allImgs.length; i<j; i++){
            var img = allImgs[i];
            if(!img.getAttribute('loaded') && img.offsetTop < height){
                img.setAttribute('src', img.getAttribute('lazy-src'))
                img.setAttribute('loaded', 'true')
            }
        }
    }

    function getImgUrl(user){
        if(!user.images[0].imageUrl){
            return ''
        }
        var url = user.images[0].imageUrl;
        var tempArr = url.split('!')
        if(tempArr.length > 1){
            var params = tempArr[1].split('.')
            var resolution = params[0], imgType = '';
            if(params.length > 1){
                imgType = params[1]
            }
            var resolutionArr = resolution.split('x');
            if(resolutionArr.length > 1){
                var width = resolutionArr[0];
                var height = resolutionArr[1];
                var newWidth = parseInt(window.innerWidth * 0.5);
                var newHeight = parseInt(newWidth * height/width);
                user.newHeight = newWidth * height/width;
                user.height = newHeight;
                user.width = newWidth;
                return tempArr[0] + '!' + newWidth + 'x' + newHeight + '.' + imgType;
            }
            return tempArr[0] + resolutionArr[0] + imgType ? '.' + imgType : '';
        }else{
            return url
        }
    }
})()

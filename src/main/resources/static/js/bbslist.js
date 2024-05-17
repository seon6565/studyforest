function bbsList(parentdivid,servletUrl,category,pagingParameter){
    let parentdiv = document.getElementById(parentdivid);
    parentdiv.innerHTML = ``;
    parentdiv.innerHTML += `<div id="bbssearch">
        <form name="frmSearch" id="search" action="/bbs/list/free">
            <div class="input-group mb-1">
                <span class="input-group-text ">검색범위</span>
                <div class="input-group-text">
                    <div class="form-check form-switch form-check-inline" >
                        <label class="form-check-label" for="search_type_0">제목</label>
                        <input class="form-check-input" role="switch" type="checkbox" value="t" name="search_type" id="search_type_0">
                    </div>
                    <div class="form-check form-switch form-check-inline" >
                        <label class="form-check-label" for="search_type_0">내용</label>
                        <input class="form-check-input" role="switch" type="checkbox" value="c" name="search_type" id="search_type_1">
                    </div>
                </div>
                <input class="form-control" type="text" name="search_word" id="search_word" placeholder="검색어">
            </div>
            <div class="input-group mb-1">
                <span class="input-group-text">학습 노출 기간</span>
                <input type="date" class="form-control" name="search_date1" id="search_date1" placeholder="등록일 시작" >
                <span class="input-group-text">~</span>
                <input type="date" class="form-control" name="search_date2" id="search_date2" placeholder="등록일 끝" >
            </div>
            <div class="d-grid gap-2 d-md-flex justify-content-md-end mb-1">
                <button class="btn btn-outline-primary" type="submit">검색</button>
                <button class="btn btn-outline-primary" type="reset" onclick="bbsList('maincontent','/bbs/list','${category}')">검색 초기화</button>
            </div>
        </form>
    </div>`;
    parentdiv.innerHTML += `<div id="table">
        <table class="table" >
            <thead>
            <tr class="table-secondary row">
                <th class="col-1 text-truncate">No</th>
                <th class="col-1 text-truncate">카테고리</th>
                <th class="col-8 text-truncate">제목</th>
                <th class="col-1 text-truncate">작성자</th>
                <th class="col-1 text-truncate">등록일</th>
            </tr>
            </thead>
            <tbody id="tablebody">

            </tbody>
        </table>
        <div class="float-end">
            <ul class="pagination flex-wrap" id="paging">
            
            </ul>
        </div>
    </div>`;

    let tablebody = document.getElementById('tablebody');
    let pagingbody = document.getElementById('paging');
    const xhr = new XMLHttpRequest();
    xhr.open("get", servletUrl + "/" + category+pagingParameter);
    xhr.send();
    xhr.onload = function () {
        let result = JSON.parse(xhr.response);
        if (xhr.status == 200) {
            for (let i = 0; i < result.dtoList.length; i++) {
                tablebody.innerHTML += `<tr class="row">
                    <td class="col-1 text-truncate" id="user_id${i}">${result.dtoList[i].bbsIdx}</td>
                    <td class="col-1 text-truncate" id="user_id${i}">${result.dtoList[i].category2}</td>
                    <td class="col-8 text-truncate" id="title${i}">
                        <a href="/">${result.dtoList[i].title}</a>
                    </td>
                    <td class="col-1 text-truncate" id="user_id${i}">${result.dtoList[i].user_id}</td>
                    <td class="col-1 text-truncate" id="category${i}">${result.dtoList[i].reg_date}</td>
                </tr>`;
            }
            if(result.prev_page_flag==true){
                pagingbody.innerHTML +=`
                <li class="page-item">
                    <a onclick="bbsList('maincontent','/bbs/list','${category}','?page=${result.page_block_start - 10}${result.linkParams}')" class="page-link" data-num="${result.page_block_start - 10}">Previous</a>
                </li>`;
            }
            for (let i = result.page_block_start; i < result.page_block_end; i++) {
                if (result.page == i) {
                    pagingbody.innerHTML += `
                    <li class="page-item active">
                        <a class="page-link" data-num=${i}>${i}</a>
                    </li>`;
                } else {
                    pagingbody.innerHTML += `
                    <li class="page-item">
                        <a onclick="bbsList('maincontent','/bbs/list','${category}','?page=${i}${result.linkParams}')" class="page-link" data-num="${i}">${i}</a>
                    </li>`;
                }
            }
            if (result.next_page_flag == true) {
                pagingbody.innerHTML +=`
                <li class="page-item">
                    <a onclick="bbsList('maincontent','/bbs/list','${category}','?page=${result.page_block_end + 1}${result.linkParams}')" class="page-link" data-num="${result.page_block_end + 1}">Next</a>
                </li>
            `;
            }
        }
        }
    }
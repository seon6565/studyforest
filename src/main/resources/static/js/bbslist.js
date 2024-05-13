function bbsList(bodyid,category,servletUrl){
    let tablebody = document.getElementById(bodyid);
    const xhr = new XMLHttpRequest();
    xhr.open("get",servletUrl+category);
    xhr.send();
    xhr.onload = function (){
        console.log(xhr.response);
        let result = JSON.parse(xhr.response);
        console.log(result);
        if(xhr.status==200){
            for(let i = 0; i<result.bbsDTO.content.length;i++) {
                tablebody.innerHTML += `<tr class="row">
                    <td class="col-2 text-truncate" id="user_id${i}">${result.bbsDTO.content[i].user_id}</td>
                    <td class="col-2 text-truncate" id="category${i}">${result.bbsDTO.content[i].category}</td>
                    <td class="col-6 text-truncate" id="title${i}">
                        <a href="/bbs/">${result.bbsDTO.content[i].title}</a>
                    </td>
                    <td class="col-2 text-truncate" id="reg_date">${result.bbsDTO.content[i].reg_date}</td>
                </tr>`;
            }
        }
    }
}
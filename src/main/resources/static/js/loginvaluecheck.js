
function checkloginid(checkid,btnid,errid) {
    let Check = document.getElementById(checkid);
    let Char = Check.value;
    let btn = document.getElementById(btnid);
    let err = document.getElementById(errid);
    console.log(err);
    const regex = /^[a-z0-9]{4,15}$/;
    if (regex.test(Char)) {
        err.style.display = "none";
        btn.disabled = false;
    } else {
        err.style.display = "block";
        btn.disabled = true;
    }
}
function checkloginpw(checkid, btnid,errid) {
    let Check = document.getElementById(checkid);
    let Char = Check.value;
    let btn = document.getElementById(btnid);
    let err = document.getElementById(errid);
    console.log(err);
    const regex = /^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,20}$/;
    if (regex.test(Char)) {
        err.style.display = "none";
        btn.disabled = false;
    } else {
        err.style.display = "block";
        btn.disabled = true;
    }
}

function checkloginall(frmid, userid, pwdid){

    const frm = document.getElementById("frmid");
    const iddom = document.getElementById("userid");
    const pwddom = document.getElementById("pwdid");
    frm.preventDefault();
    frm.stopPropagation();
    id = iddom.value;
    pwd = pwddom.value;
        const regex = /^[a-z0-9]{4,15}$/;
        const regex2 = /^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,20}$/;
        if(!regex.test(id)){
            alert("영어 소문자 및 숫자만 입력가능합니다.");
        }
        else if(!regex2.test(pwd)){
            alert("영어+숫자+특수문자 조합으로 최소 8자리 이상만 허용됩니다.");
        }
        else {
            frm.method = "post";
            frm.submit();
        }
}


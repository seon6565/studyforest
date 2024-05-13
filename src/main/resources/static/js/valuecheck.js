function checkInputCommon(id,textid){
    let Check = document.getElementById(id);
    let Char = Check.value;
    let text = document.getElementById(textid);
    const regex = /^[가-힣a-zA-Z0-9\s]{1,50}$/;
    if(regex.test(Char)){
        text.style.display = "none";
    }
    else{
        text.style.display = "block";
        Check.focus();
        return false;
        event.preventDefault();
    }
    return true;
}

function checkInputAddrNumber(id,textid){
    let Check = document.getElementById(id);
    let Char = Check.value;
    let text = document.getElementById(textid);
    let now = Date.now();
    let input = new Date(Char)
    const regex = /^[0-9]{5}$/;
    if(regex.test(Char)){
        text.style.display = "none";
    }
    else{
        text.style.display = "block";
        Check.focus();
        return false;
        event.preventDefault();
    }
    return true;
}

function checkInputId(id,textid,okflagid){

    document.getElementById(okflagid).value="0";
    let Check = document.getElementById(id);
    let Char = "";
    if(Check.value!=null) {
        Char = Check.value;
    }
    let text = document.getElementById(textid);
    const regex = /^[A-Za-z0-9]{5,15}$/;
    if(regex.test(Char)){
        text.style.display = "none";
    }
    else{
        text.style.display = "block";
        Check.focus();
        return false;
        event.preventDefault();
    }
    //중복검사
    return true;
}

function idduplecheck(id,errordisplayId,okflagid,servletUrl){
    let checkid = document.getElementById(id);
    const xhr = new XMLHttpRequest();
    xhr.open("get",servletUrl+"?user_id="+checkid.value);
    xhr.send();
    xhr.onload = function (){
        if(xhr.status==200){
            if(xhr.response == "N"){
                document.getElementById(errordisplayId).style.display = "block";
                document.getElementById(okflagid).value="0";
                alert("이미 존재하거나 탈퇴한 회원의 아이디입니다.");
            }
            else{
                document.getElementById(errordisplayId).style.display = "none"
                document.getElementById(okflagid).value="1";
                alert("중복 아이디가 없습니다.");
            }
        }
    }
}

function checkInputPw(id, textid) {
    let Check = document.getElementById(id);
    let Char = Check.value;
    let text = document.getElementById(textid);
    const regex = /^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{10,16}$/;
    if (regex.test(Char)) {
        text.style.display = "none";
    } else {
        text.style.display = "block";
        Check.focus();
        return false;
        event.preventDefault();
    }
    return true;
}

function checkInputPw2(id, id2, textid) {
    let Check = document.getElementById(id);
    let Check2 = document.getElementById(id2);
    let Char = Check.value;
    let Char2 = Check2.value;
    let text = document.getElementById(textid);
    const regex = /^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{10,16}$/
    if(regex.test(Char2)&& Char==Char2){
        text.style.display = "none";
    }
    else{
        text.style.display = "block";
        Check2.focus();
        return false;
        event.preventDefault();
    }
    return true;
}

function checkInputName(id,textid){
    let Check = document.getElementById(id);
    let Char = Check.value;
    let text = document.getElementById(textid);
    const regex = /^[가-힣A-Za-z]{1,10}$/;
    if(regex.test(Char)){
        text.style.display = "none";
    }
    else{
        text.style.display = "block";
        Check.focus();
        return false;
        event.preventDefault();
    }
    return true;
}

function checkInputBirth(id,textid){
    let Check = document.getElementById(id);
    let Char = Check.value;
    let text = document.getElementById(textid);
    let now = Date.now();
    let input = new Date(Char)
    if(now>input){
        text.style.display = "none";
    }
    else{
        text.style.display = "block";
        Check.focus();
        return false;
        event.preventDefault();
    }
    return true;
}
function checkInputBirthFourteen(y,m,d,textid){
    const date1 = new Date(y,m,d);
    let text = document.getElementById(textid);
    let year = document.getElementById(y);
    let month = document.getElementById(m);
    let day = document.getElementById(m);
    let Char = year.value;
    let Char2 = month.value;
    let Char3 = day.value;

    if((Char==0 && Char2==0&& Char3==0)){
        text.style.display = "block";
        year.focus();
        return false;
        event.preventDefault();
    }
    else{
        let birthday = new Date(Char,Char2,Char3);
        let now = new Date();
        let dd = String(now.getDate()).padStart(2, '0');
        let mm = String(now.getMonth() + 1).padStart(2, '0');
        let yyyy = now.getFullYear();
        let nowYMD = yyyy + '-' + mm + '-' + dd;
        let today = new Date(nowYMD);
        let years = today.getFullYear() - birthday.getFullYear();
        birthday.setFullYear(today.getFullYear());
        if (today < birthday)
        {
            years--;
        }
        if(years>=14){
            text.style.display = "none";
        }
        else{
            text.style.display = "block";
            year.focus();
            return false;
            event.preventDefault();
        }
    }
    return true;
}

function checkInputPhone(id,textid){
    let Check = document.getElementById(id);
    let Char = Check.value;
    let text = document.getElementById(textid);
    const regex = /\d{2,3}-\d{3,4}-\d{4}/g
    if(regex.test(Char)){
        text.style.display = "none";
    }
    else{
        text.style.display = "block";
        Check.focus();
        return false;
        event.preventDefault();
    }
    return true;
}

function checkInputEmail(id,textid){
    let Check = document.getElementById(id);
    let Char = Check.value;
    let text = document.getElementById(textid);
    const regex = /[@]*[.]/;
    if(regex.test(Char)){
        text.style.display = "none";
    }
    else{
        text.style.display = "block";
        Check.focus();
        return false;
        event.preventDefault();
    }
    return true;
}


function checkInputInterest(id,id2,textid){
    let Check = document.getElementById(id);
    let Check2 = document.getElementById(id2);

    let Char = Check.value;
    let Char2 = Check2.value;
    console.log(Char,Char2);
    let text = document.getElementById(textid);
    if((Char==0 && Char2==0)){
        text.style.display = "block";
        Check.focus();
        return false;
        event.preventDefault();
    }
    else{
        text.style.display = "none";
    }
    return true;
}
function checkInputCheckBox(id,textid){

    let Check = document.getElementById(id);
    let text = document.getElementById(textid);
    if(Check.checked==true){
        text.style.display = "none";
    }
    else{
        text.style.display = "block";
        Check.focus();
        event.preventDefault();
        return false;
    }
    return true;
}
function checklast(user_id,div_err_user_id
    ,pwd,div_err_pwd
    ,name,div_err_name
    ,email,div_err_email
    ,phone_number,div_err_phone_number
    ,birthday,div_err_birthday
    ,okflagid
    ,addr_number,div_err_addr_number
    ,addr1,div_err_addr1
    ,addr2,div_err_addr2){
    if(checkInputPw(pwd,div_err_pwd)
        &&checkInputName(name,div_err_name)
        &&checkInputEmail(email,div_err_email)
        &&checkInputPhone(phone_number,div_err_phone_number)
        &&checkInputBirth(birthday,div_err_birthday)
        &&checkInputAddrNumber(addr_number,div_err_addr_number)
        &&checkInputCommon(addr1,div_err_addr1)
        &&checkInputCommon(addr2,div_err_addr2)){
        if(document.getElementById(okflagid).value >0) {
            if(checkInputId(user_id,div_err_user_id,okflagid)) {
                alert("가입되었습니다.");
            }
            else{
                alert("아이디는 5~15자내의 숫자,영어만 입력하세요.");
                event.preventDefault();
            }
        }
        else{
            alert("아이디 중복 체크를 진행해주세요.");
            event.preventDefault();
        }
    }
    else{
        alert("조건에 맞게 수정해주세요.");
        event.preventDefault();
    }
}
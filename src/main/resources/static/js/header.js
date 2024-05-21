function header(){
    document.write(`<nav class="navbar navbar-expand-lg navbar-dark bg-dark ftco_navbar ftco-navbar-light" id="ftco-navbar">
    <div class="container d-flex align-items-center">
        <a class="navbar-brand" href="/">Study Forest</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="oi oi-menu"></span> Menu
        </button>
        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active"><a href="/" class="nav-link pl-0">Home</a></li>
                <li class="nav-item"><a href="/bbs" class="nav-link">전체게시판</a></li>
                <li class="nav-item"><a href="/member/regist" class="nav-link">회원가입</a></li>
                <li class="nav-item"><a href="/autologin" class="nav-link">로그인</a></li>
            </ul>
        </div>
    </div>
</nav>
`);
}
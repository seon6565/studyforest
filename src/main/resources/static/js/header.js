function header(){
    document.write(`<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid" >
        <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
            <img src="/img/tree.svg" width="40" height="32" class="bi me-2">
            <span class="fs-4">Study Forest</span>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item"><a href="/" class="nav-link link-secondary">전체게시판</a></li>
<!--                <li class="nav-item">-->
<!--                    <a href="/member/view" class="nav-link link-secondary" id="userinfo">님 환영합니다.</a>-->
<!--                </li>-->
                <li class="nav-item"><a href="/member/regist" class="nav-link link-secondary">회원가입</a></li>
<!--                <li class="nav-item"><a href="/logout" class="nav-link link-secondary">로그아웃</a></li>-->
                <li class="nav-item"><a href="/autologin" class="nav-link link-secondary">로그인</a></li>
            </ul>
        </div>
    </div>
</nav>
`);
}
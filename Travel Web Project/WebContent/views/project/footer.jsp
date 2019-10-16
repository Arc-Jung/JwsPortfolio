<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<footer class="ftco-footer ftco-bg-dark ftco-section">
      <div class="container">
        <div class="row">
          <div class="col-sm-12">
            <div class="ftco-footer-widget">
              <h2 class="ftco-heading-2">사이트 맵</h2>
            </div>
          </div>
        </div>
          
         <div class="row">
          <div class="col-sm-12 col-md-3">
            <div class="ftco-footer-widget">
              <h2 class="ftco-heading-2">소개</h2>
              <ul class="list-unstyled">
                <li><a href="#" class="py-2 d-block">프로젝트 소개</a></li>
                <li><a href="#" class="py-2 d-block">개발자를 소개합니다</a></li>
                <li><a href="#" class="py-2 d-block">이용방법</a></li>
              </ul>
            </div>
          </div>
          
          <div class="col-sm-12 col-md-3">
              <div class="ftco-footer-widget">
                  <h2 class="ftco-heading-2">여행정보</h2>
                  <ul class="list-unstyled">
                      <li><a href="${pageContext.request.contextPath}/info/category.do?category=A" class="py-2 d-block">수도권</a></li>
                      <li><a href="${pageContext.request.contextPath}/info/category.do?category=B" class="py-2 d-block">강원권</a></li>
                      <li><a href="${pageContext.request.contextPath}/info/category.do?category=C" class="py-2 d-block">경상권</a></li>
                      <li><a href="${pageContext.request.contextPath}/info/category.do?category=D" class="py-2 d-block">전라권</a></li>
                      <li><a href="${pageContext.request.contextPath}/info/category.do?category=E" class="py-2 d-block">제주권</a></li>
                  </ul>
              </div>
          </div>
          
          <div class="col-sm-12 col-md-3">
             <div class="ftco-footer-widget">
              <h2 class="ftco-heading-2">커뮤니티</h2>
              <ul class="list-unstyled">
                <li><a href="${pageContext.request.contextPath}/content/category.do?category=A" class="py-2 d-block">여행후기 &#38; 팁</a></li>
                <li><a href="${pageContext.request.contextPath}/content/category.do?category=B" class="py-2 d-block">여행지 Q&#38;A</a></li>
                <li><a href="${pageContext.request.contextPath}/content/category.do?category=C" class="py-2 d-block">자유게시판</a></li>
                <c:if test="${null ne sessionScope.loginID}">          
          	      <li><a href="${pageContext.request.contextPath}/content/category.do?category=D" class="py-2 d-block">내가 쓴 글</a></li>
                </c:if>
              </ul>
            </div>
          </div>
          
          <div class="col-sm-12 col-md-3">
              <div class="ftco-footer-widget">
                  <h2 class="ftco-heading-2">마이페이지</h2>
                  <ul class="list-unstyled">
                  <c:if test="${null ne sessionScope.loginID}">
                      <li><a href="${pageContext.request.contextPath}/project/myplan.do" class="py-2 d-block">여행계획만들기</a></li>
                      <li><a href="${pageContext.request.contextPath}/user/userinfo.do" class="py-2 d-block">회원정보수정</a></li>
                  </c:if>
                  <c:if test="${null eq sessionScope.loginID}">
                       <li><a href="#" class="py-2 d-block">로그인을 하세요!!</a></li>
                  </c:if>
                  </ul>
              </div>
          </div>
         </div>
          
          
        
        
        <div class="row">
          <div class="col-sm-12 text-center">
            <p>템플릿은 <i class="icon-heart" aria-hidden="true"></i> <a href="https://colorlib.com" target="_blank">Colorlib</a>의 것을 사용했습니다</p>
          </div>
        </div>
      </div>
    </footer>

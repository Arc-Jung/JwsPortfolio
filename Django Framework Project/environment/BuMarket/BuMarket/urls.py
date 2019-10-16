"""BuMarket URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.11/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""

"""
View 클래스 대신 ViewSet 클래스를 사용했기때문에
URL 설정할 필요가 없음.
Router를 사용하면 뷰코드와 뷰, URL이 자동 연결된다.
나머지는 REST 프레임워크가 알아서 다 한다.
"""
from django.conf.urls import url, include
from rest_framework import routers
from rest_framework_swagger.views import get_swagger_view
from django.contrib import admin
from . import view
from drf_yasg.views import get_schema_view
from rest_framework.permissions import AllowAny
from drf_yasg import openapi

router = routers.DefaultRouter()
#router.register(r'test',view.TestViewSet)
#router.register(r'users', view.UserViewSet)
#router.register(r'product',view.ProductViewSet)
#router.register(r'login',view.LoginViewSet)
router.register(r'like',view.LikeViewSet)
router.register(r'sale',view.SaleViewSet)
router.register(r'image',view.ImageViewSet)

urlpatterns = [
    url('test',view.testStrDef, name='test'),
    url('json',view.testJsonDef, name='json'),
    url('product',view.testProductDef, name='product'),
    url('login',view.LoginViewSet.userlogin, name='login'),
    url('database',view.TestViewSet.testDatabaseDef, name='testDatabaseDef'),
]
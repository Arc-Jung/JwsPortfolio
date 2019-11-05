#-*- coding: utf-8 -*- 
# 한글을 사용하면 위에 꼭 써야함

from django.urls import path, include
from rest_framework import routers
from django.conf.urls import url
from django.contrib import admin

from .import views

router = routers.DefaultRouter()
router.register(r'product', views.ProductViewSet)
router.register(r'user', views.UserViewSet)
router.register(r'like', views.LikeViewSet)
router.register(r'sale', views.SaleViewSet)
router.register(r'image', views.ImageViewSet)
router.register(r'userimage', views.UserImageViewSet)
router.register(r'proimage', views.ProImageViewSet)

urlpatterns=[
    url(r'^', include(router.urls)),
    #path('',views.ProductViewSet, name='post_list'),
]
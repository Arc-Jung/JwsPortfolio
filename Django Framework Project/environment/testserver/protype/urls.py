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

urlpatterns=[
    url(r'^', include(router.urls)),
    #path('',views.ProductViewSet, name='post_list'),
]
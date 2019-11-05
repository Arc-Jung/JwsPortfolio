from django.contrib import admin
from django.urls import path
from rest_framework import routers
from django.conf.urls import url, include
from . import views

urlpatterns = [
    url(r'user', views.user, name='user'),
    url(r'product', views.createProduct, name='product'),
    ]
    
    # 숫자로 이루어진 question_id를 매개변수로 저장해서 views.py에 넘긴다
    #url(r'^(?P<question_id>[0-9]+)/$', views.detail, name='detail'),
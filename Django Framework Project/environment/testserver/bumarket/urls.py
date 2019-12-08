from django.contrib import admin
from django.urls import path
from rest_framework import routers
from django.conf.urls import url, include
from . import views
from .models import UserModel, ProductModel, SaleModel, LikeModel, SaleModel, ImageModel, UserImageModel, ProImageModel # Model import
from .serializers import UserSerializer, ProductSerializer, LikeSerializer, SaleSerializer, ImageSerializer, UserImageSerializer, ProImageSerializer # Serializer import
from rest_framework import generics

urlpatterns = [
    url(r'user/', views.UserList.as_view(), name='userlist'),
    url(r'userdetail/', views.UserDetail.as_view(), name='userdetail'),
    url(r'product/', views.ProductList.as_view(), name='all'),
    url(r'category/', views.category.as_view(), name='category'),
    url(r'regist/', views.Regist.as_view(), name='regist'),
    url(r'login/', views.Login.as_view(), name='login'),
    url(r'imagerequest/', views.ImageRequest.as_view(), name='imagerequest'),
    url(r'imageupload/', views.ImageUpload.as_view(), name='imageupload'),
    url(r'producttype/', views.ProductType.as_view(), name='producttype'),
    url(r'searchall/', views.SearchAll.as_view(), name='searchall'),
    url(r'salehistory/', views.SaleHistory.as_view(), name='searchall'),
    #url(r'Image/', views.Image.as_view(), name='Image'),
    ]
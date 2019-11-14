from django.contrib import admin
from django.urls import path
from rest_framework import routers
from django.conf.urls import url, include
from . import views
from .models import UserModel, ProductModel, SaleModel, LikeModel, SaleModel, ImageModel, UserImageModel, ProImageModel # Model import
from .serializers import UserSerializer, ProductSerializer, LikeSerializer, SaleSerializer, ImageSerializer, UserImageSerializer, ProImageSerializer # Serializer import
from rest_framework import generics

urlpatterns = [
    url(r'^/user', views.UserList.as_view(queryset=UserModel.objects.all(), serializer_class=UserSerializer), name='userlist'),
    url(r'^/product/all', views.ProductList.as_view(queryset=ProductModel.objects.all(), serializer_class=ProductSerializer), name='product/all'),
    url(r'^/product/book', views.ProductList.as_view(queryset=ProductModel.objects.all().filter(ProductCategory="도서"), serializer_class=ProductSerializer), name='product/book'),
    url(r'^/product/food', views.ProductList.as_view(queryset=ProductModel.objects.all().filter(ProductCategory="음식"), serializer_class=ProductSerializer), name='product/food'),
    ]
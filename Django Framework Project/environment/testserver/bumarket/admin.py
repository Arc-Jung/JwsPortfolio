#-*- coding: utf-8 -*- 
# 한글을 사용하면 위에 꼭 써야함

from django.contrib import admin
from .models import UserModel, ProductModel, SaleModel, LikeModel, SaleModel, ImageModel, UserImageModel, ProImageModel # Model import

admin.site.register(UserModel) # 관리하고 싶은 모델
admin.site.register(ProductModel) # 관리하고 싶은 모델
admin.site.register(LikeModel) # 관리하고 싶은 모델
admin.site.register(SaleModel) # 관리하고 싶은 모델
admin.site.register(ImageModel) # 관리하고 싶은 모델
admin.site.register(UserImageModel) # 관리하고 싶은 모델
admin.site.register(ProImageModel) # 관리하고 싶은 모델
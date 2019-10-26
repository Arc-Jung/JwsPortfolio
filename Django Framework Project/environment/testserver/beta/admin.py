#-*- coding: utf-8 -*- 
# 한글을 사용하면 위에 꼭 써야함

from django.contrib import admin
from .models import UserModel,ProductModel # 관리자로 모델 추가 삭제를 하고 싶은 모델 입력

admin.site.register(UserModel) # 관리하고 싶은 모델
admin.site.register(ProductModel) # 관리하고 싶은 모델
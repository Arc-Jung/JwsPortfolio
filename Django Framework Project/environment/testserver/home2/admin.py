from django.contrib import admin
from .models import UserModel # 관리자로 모델 추가 삭제를 하고 싶은 모델 입력

admin.site.register(UserModel) # 관리하고 싶은 모델
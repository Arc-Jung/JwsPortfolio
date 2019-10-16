from django.contrib import admin
# Register your models here.
from .models import UserModel, ProductModel, SaleModel, LikeModel # Model import

class UserAdmin(admin.ModelAdmin):
    pass

class ProductAdmin(admin.ModelAdmin):
    pass

admin.site.register(UserModel, UserAdmin)
#admin.site.register(, Admin)

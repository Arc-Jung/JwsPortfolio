from django.urls import path
from .import views

urlpatterns=[
    path('',views.ProductViewSet, name='post_list'),
]
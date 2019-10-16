#-*- coding: utf-8 -*- 
# 한글을 사용하면 위에 꼭 써야함
from django.core.exceptions import ObjectDoesNotExist, MultipleObjectsReturned
from rest_framework import filters
from rest_framework import viewsets
from rest_framework.decorators import detail_route
from rest_framework.response import Response
from .forms import UserJoinForm
from .models import User, Product
from .serializers import UserSerializer, ProductSerializer
from django.http import JsonResponse
from django.http import HttpResponse
import logging
logger = logging.getLogger('test')
from django.contrib.auth import login, authenticate #20191002

####################################################################
# JSON 예시
def post_list3(request):
    return JsonResponse({
        'message' : '안녕 파이썬 장고',
        'items' : ['파이썬', '장고', 'AWS', 'Azure'],
    }, json_dumps_params = {'ensure_ascii': True})
####################################################################

class UserViewSet(viewsets.ModelViewSet):
    queryset = User.objects.all()
    serializer_class = UserSerializer # JSON 으로 파싱하는 과정이다.
    
    def create(self, request, *args, **kwargs):
        userForm = UserJoinForm(request.POST) # 안드로이드에서 POST 방식으로 발송
        result = {} # 장고가 JSON 데이터를 담아서 보낼 배열
        idMultipleObject = True
        
        if userForm.is_valid():
            try:
                User.objects.get(id=userForm.data['UserId'])
                idMultipleObject = False
                User.objects.get(Password=userForm.data['Password'])
                User.objects.get(PasswordCheck=userForm.data['PasswordCheck'])
                if userForm.data['Password'] != userForm.data['PasswordCheck']: # 패스워드와 패스워드 확인 값이 일치하지 않으면 에러 메시지를 표시한다.
                    logger.error('PasswordCheck Error')
                User.objects.get(UserName=userForm.data['UserName'])
                User.objects.get(Phone=userForm.data['Phone'])
                User.objects.get(KakaoId=userForm.data['KakaoId'])
                User.objects.get(Email=userForm.data['Email'])
            except MultipleObjectsReturned as e:
                logger.error('Insert Error')
                result['result'] = 'Insert Error'
                return JsonResponse(result)
            
            user = userForm.save() # 저장
            result['result'] = 'complete'
            result['UserId'] = user.UserId
            result['Password'] = user.Password
            result['UserName'] = user.UserName
            result['Phone'] = user.Phone
            result['KakaoId'] = user.KakaoId
            result['Email'] = user.Email
            return JsonResponse(result)

        else :
            logger.error('Join Error')
            result['result'] = 'Join Error'
            return JsonResponse(result)

####################################################################

class LoginViewSet(viewsets.ModelViewSet):
    queryset = User.objects.all()
    serializer_class = UserSerializer # JSON 으로 파싱하는 과정이다.
    
    def userlogin(self, request, *args, **kwargs):
        result = {} # 장고가 JSON 데이터를 담아서 보낼 배열
        id = request.POST['id'] # 안드로이드에서 POST 방식으로 발송
        pw = request.POST['pw'] # 안드로이드에서 POST 방식으로 발송
        usercheck = authenticate(UserId=id, Password=pw) # 데이터 베이스의 컬럼값과 포스트로 보내진 데이터를 비교
        if (usercheck != ''):
            login(request,user=usercheck)
            result['result'] = 'Login seuccess'
            return JsonResponse(result)
        else:
            result['result'] = 'Login fail'
            return JsonResponse(result)
            
    def userlogout(self, request, *args, **kwargs):
        return ''

    def usermypage(self, request, *args, **kwargs):
        return ''
        
    def usermodify(self, request, *args, **kwargs):
        return ''
        
    def userprofileview(self, request, *args, **kwargs):
        return ''
####################################################################

class ProductViewSet(viewsets.ModelViewSet):
    queryset = Product.objects.all()
    serializer_class = ProductSerializer # JSON 으로 파싱하는 과정이다.
    
    def product(self, request, *args, **kwargs):
        return ''
        
    def delete(self, request, *args, **kwargs):
        return ''
        
    def sale(self, request, *args, **kwargs):
        return ''
        
    def shop(self, request, *args, **kwargs):
        return ''
        
    def serch(self, request, *args, **kwargs):
        return ''
        
####################################################################

class FileViewSet





class file
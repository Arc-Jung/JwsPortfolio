#-*- coding: utf-8 -*- 
# 한글을 사용하면 위에 꼭 써야함
from django.core.exceptions import ObjectDoesNotExist, MultipleObjectsReturned
from rest_framework import filters
from rest_framework import viewsets
from rest_framework import permissions
#from rest_framework.decorators import detail_route
from rest_framework.response import Response
from .forms import UserJoinForm
from .models import UserModel, ProductModel, SaleModel, LikeModel # Model import
from .serializers import UserSerializer, ProductSerializer
from django.http import JsonResponse
from django.http import HttpResponse
import logging
logger = logging.getLogger('test')
from django.contrib.auth import login, authenticate #20191002
from django.http import HttpResponse #20191004
from rest_framework.permissions import AllowAny
from rest_framework.response import Response
from rest_framework.schemas import SchemaGenerator
from rest_framework.views import APIView
from rest_framework_swagger import renderers
from rest_framework import status
from rest_framework.response import Response
####################################################################


def get_extra1(request):
    return HttpResponse("extra1")
    
def get_list_by_user(request, username):
    print("username : ", username)
    return HttpResponse("{}의 블로그 글 리스트가 출력됩니다!".format(username))
    
def test(request, username):
    return HttpResponse("넘어온 데이터 : "+username)
    
    
####################################################################

class UserViewSet(viewsets.ModelViewSet):
    queryset = UserModel.objects.all()
    serializer_class = UserSerializer # JSON 으로 파싱하는 과정이다.
    
    def create(self, request, *args, **kwargs):
        userForm = UserJoinForm(request.POST) # 안드로이드에서 POST 방식으로 발송
        result = {} # 장고가 JSON 데이터를 담아서 보낼 배열
        idMultipleObject = True
        
        if userForm.is_valid():
            try:
                UserModel.objects.get(id=userForm.data['UserId'])
                idMultipleObject = False
                UserModel.objects.get(Password=userForm.data['Password'])
                UserModel.objects.get(PasswordCheck=userForm.data['PasswordCheck'])
                if userForm.data['Password'] != userForm.data['PasswordCheck']: # 패스워드와 패스워드 확인 값이 일치하지 않으면 에러 메시지를 표시한다.
                    logger.error('PasswordCheck Error')
                else:
                    UserModel.objects.get(UserName=userForm.data['UserName'])
                    UserModel.objects.get(Phone=userForm.data['Phone'])
                    UserModel.objects.get(KakaoId=userForm.data['KakaoId'])
                    UserModel.objects.get(Email=userForm.data['Email'])
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
    queryset = UserModel.objects.all()
    serializer_class = UserSerializer # JSON 으로 파싱하는 과정이다.
    
    def userlogin(self, request, *args, **kwargs): # POST
        result = {} # 장고가 JSON 데이터를 담아서 보낼 배열
        id = request.POST['id'] # 안드로이드에서 POST 방식으로 발송
        pw = request.POST['pw'] # 안드로이드에서 POST 방식으로 발송
        usercheck = UserModel.objects.get(UserId=id, Password=pw) 
        # 유저모델 -> 데이터베이스 -> 오브젝트 -> Get -> UserId의 값을 id 에, Password의 값을 pw에 저장한다.
        # usercheck = authenticate(UserId=id, Password=pw) # 데이터 베이스의 컬럼값과 포스트로 보내진 데이터를 비교
        if (usercheck != ''): # 데이터베이스가 값을 성공적으로 불러왔다면 로그인 성공
            result['id'] = UserModel.UserId
            result['pw'] = UserModel.Password
            result['result'] = 'Login seuccess'
            return JsonResponse(result)
        else: # 데이터베이스가 값을 가져오지 못하였다면 로그인 실패
            result['result'] = 'Login fail'
            return JsonResponse(result)
            
    def userlogout(self, request, *args, **kwargs):
        return ''

    def usermypage(self, request, *args, **kwargs):
        return ''
        
    def usermodify(self, request, *args, **kwargs):
        return ''
        
        
####################################################################


class LikeViewSet(viewsets.ModelViewSet):
    queryset = ProductModel.objects.all()
    serializer_class = ProductSerializer # JSON 으로 파싱하는 과정이다.
    
    def likeproduct(self, request, *args, **kwargs):
        return ''


####################################################################


class ProductViewSet(viewsets.ModelViewSet):
    queryset = ProductModel.objects.all()
    serializer_class = ProductSerializer # JSON 으로 파싱하는 과정이다.
    #permission_class = (permissions.IsAuthenticated)
    
    def productlist(self, request, *args, **kwargs):
        productpage = {} # 장고가 JSON 데이터를 담아서 보낼 배열
        if request.method == 'POST':
            return ''
        else:
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
        
        
class SaleViewSet(viewsets.ModelViewSet):
    queryset = ProductModel.objects.all()
    serializer_class = ProductSerializer # JSON 으로 파싱하는 과정이다.
    
    def saleview(self, request, *args, **kwargs):
        return ''
        
####################################################################

class ImageViewSet(viewsets.ModelViewSet):
    queryset = ProductModel.objects.all()
    serializer_class = ProductSerializer # JSON 으로 파싱하는 과정이다.
    
    def productupload(self, request, *args, **kwargs):
        return ''
        
    def profileupload(self, request, *args, **kwargs):
        return ''
        
    def productview(self, request, *args, **kwargs):
        return ''
        
    def profileview(self, request, *args, **kwargs):
        return ''
        
####################################################################
        
        
class TestViewSet(viewsets.ModelViewSet):
    queryset = UserModel.objects.all()
    serializer_class = UserSerializer # JSON 으로 파싱하는 과정이다.
    
    #def testview(self, request, *args, **kwargs):
        #return ('UserId', 'Password', 'UserName', 'Phone', 'KakaoId', 'Email')
    
# JSON 예시
    def posttest(self, request, word):
        self.get_
        return JsonResponse({
            'message' : '안녕 파이썬 장고',
            'items' : ['파이썬', '장고', 'AWS', 'EC2'],
        }, json_dumps_params = {'ensure_ascii': True})
        
    def testDatabaseDef(requestPId): # Get : :8000/database?requestPId=1
        requestData=requestPId.GET.get("requestPId");
        result = {} # 장고가 JSON 데이터를 담아서 보낼 배열
        queryset = ProductModel.objects.all()
    
        #print('query:' + str(queryset))
        serializer_class = ProductSerializer # JSON 으로 파싱하는 과정이다.
        MatchProductId=ProductModel.objects.get(ProductId=requestData)
    
        result['items'] = MatchProductId.ProductId
        result['price'] = MatchProductId.ProductPrice
        #return JsonResponse(result)
        return HttpResponse("프린트값: "+ str(result))
        
        
####################################################################


def testStrDef(request):
    rr=request.GET.get("id");
    return HttpResponse("성공 : "+ str(rr) + " <-리퀘스트의 값 ")
    
def testJsonDef(request):
    return JsonResponse({
            'message' : '안녕 파이썬 장고',
            'items' : ['파이썬', '장고', 'AWS', 'EC2'],
        }, json_dumps_params = {'ensure_ascii': True})
        
def testProductDef(request):
    return JsonResponse({
            'message' : '상품페이지',
            'items' : 'A상품',
        }, json_dumps_params = {'ensure_ascii': True})
    
        
        
####################################################################
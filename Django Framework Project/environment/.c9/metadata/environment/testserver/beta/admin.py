{"filter":false,"title":"admin.py","tooltip":"/testserver/beta/admin.py","undoManager":{"mark":18,"position":18,"stack":[[{"start":{"row":0,"column":0},"end":{"row":3,"column":0},"action":"remove","lines":["from django.contrib import admin","","# Register your models here.",""],"id":2},{"start":{"row":0,"column":0},"end":{"row":7,"column":46},"action":"insert","lines":["#-*- coding: utf-8 -*- ","# 한글을 사용하면 위에 꼭 써야함","","from django.contrib import admin","from .models import UserModel,ProductModel # 관리자로 모델 추가 삭제를 하고 싶은 모델 입력","","admin.site.register(UserModel) # 관리하고 싶은 모델","admin.site.register(ProductModel) # 관리하고 싶은 모델"]}],[{"start":{"row":7,"column":46},"end":{"row":8,"column":0},"action":"insert","lines":["",""],"id":3}],[{"start":{"row":8,"column":0},"end":{"row":8,"column":46},"action":"insert","lines":["admin.site.register(ProductModel) # 관리하고 싶은 모델"],"id":4}],[{"start":{"row":8,"column":46},"end":{"row":9,"column":0},"action":"insert","lines":["",""],"id":5}],[{"start":{"row":9,"column":0},"end":{"row":9,"column":46},"action":"insert","lines":["admin.site.register(ProductModel) # 관리하고 싶은 모델"],"id":6}],[{"start":{"row":9,"column":46},"end":{"row":10,"column":0},"action":"insert","lines":["",""],"id":7}],[{"start":{"row":10,"column":0},"end":{"row":10,"column":46},"action":"insert","lines":["admin.site.register(ProductModel) # 관리하고 싶은 모델"],"id":8}],[{"start":{"row":10,"column":46},"end":{"row":11,"column":0},"action":"insert","lines":["",""],"id":9}],[{"start":{"row":11,"column":0},"end":{"row":11,"column":46},"action":"insert","lines":["admin.site.register(ProductModel) # 관리하고 싶은 모델"],"id":10}],[{"start":{"row":11,"column":46},"end":{"row":12,"column":0},"action":"insert","lines":["",""],"id":11}],[{"start":{"row":12,"column":0},"end":{"row":12,"column":46},"action":"insert","lines":["admin.site.register(ProductModel) # 관리하고 싶은 모델"],"id":12}],[{"start":{"row":8,"column":20},"end":{"row":8,"column":32},"action":"remove","lines":["ProductModel"],"id":13},{"start":{"row":8,"column":20},"end":{"row":8,"column":29},"action":"insert","lines":["LikeModel"]}],[{"start":{"row":9,"column":20},"end":{"row":9,"column":32},"action":"remove","lines":["ProductModel"],"id":14},{"start":{"row":9,"column":20},"end":{"row":9,"column":29},"action":"insert","lines":["SaleModel"]}],[{"start":{"row":10,"column":20},"end":{"row":10,"column":32},"action":"remove","lines":["ProductModel"],"id":15},{"start":{"row":10,"column":20},"end":{"row":10,"column":30},"action":"insert","lines":["ImageModel"]}],[{"start":{"row":11,"column":20},"end":{"row":11,"column":32},"action":"remove","lines":["ProductModel"],"id":16},{"start":{"row":11,"column":20},"end":{"row":11,"column":34},"action":"insert","lines":["UserImageModel"]}],[{"start":{"row":12,"column":20},"end":{"row":12,"column":32},"action":"remove","lines":["ProductModel"],"id":17},{"start":{"row":12,"column":20},"end":{"row":12,"column":33},"action":"insert","lines":["ProImageModel"]}],[{"start":{"row":4,"column":0},"end":{"row":5,"column":0},"action":"remove","lines":["from .models import UserModel,ProductModel # 관리자로 모델 추가 삭제를 하고 싶은 모델 입력",""],"id":18},{"start":{"row":4,"column":0},"end":{"row":5,"column":168},"action":"insert","lines":["from .models import UserModel, ProductModel, SaleModel, LikeModel, SaleModel, ImageModel, UserImageModel, ProImageModel # Model import","from .serializers import UserSerializer, ProductSerializer, LikeSerializer, SaleSerializer, ImageSerializer, UserImageSerializer, ProImageSerializer # Serializer import"]}],[{"start":{"row":5,"column":0},"end":{"row":5,"column":166},"action":"remove","lines":["from .serializers import UserSerializer, ProductSerializer, LikeSerializer, SaleSerializer, ImageSerializer, UserImageSerializer, ProImageSerializer # Serializer impo"],"id":19}],[{"start":{"row":5,"column":1},"end":{"row":5,"column":2},"action":"remove","lines":["t"],"id":21},{"start":{"row":5,"column":0},"end":{"row":5,"column":1},"action":"remove","lines":["r"]}]]},"ace":{"folds":[],"scrolltop":0,"scrollleft":0,"selection":{"start":{"row":12,"column":40},"end":{"row":12,"column":40},"isBackwards":false},"options":{"guessTabSize":true,"useWrapMode":false,"wrapToView":true},"firstLineState":{"row":48,"mode":"ace/mode/python"}},"timestamp":1572409734860,"hash":"7266eb6c9f97f0c428d6b42b002362f69284fe82"}
CNN 알고리즘을 이용한 동물 사진 분류 프로젝트
===========================================================================================
<img src="https://raw.githubusercontent.com/dsg890789/JwsPortfolio/master/CNN%20Model%20Project/Tensorflow.png">

<img src="https://raw.githubusercontent.com/dsg890789/JwsPortfolio/master/CNN%20Model%20Project/AWS%20SageMaker-5.png">
    
> Technology stack : AWS SageMaker / AWS S3 Bucket / AWS CloudWatch / CNN / TensorFlow

프로젝트 개요
-------------------------------------------------------------------------------------------
- CNN 알고리즘을 통하여 S3 버켓에 들어있는 특정 동물 사진의 동물 사진을 AI를 통하여 분류하는 API를 개발한다.
- Amazon Web Service SageMaker를 통하여 모델을 구축한다.
- 사용 인스턴스는 ml.m5.xlarge 이며 4vCPU 16GB 의 성능을 가진 강력한 인스턴스.
- 이미지 데이터세은 동물 10여종의 5만여장의 사진 중 5000여장을 사용.

CNN 알고리즘
-------------------------------------------------------------------------------------------
<img src="https://raw.githubusercontent.com/dsg890789/JwsPortfolio/master/CNN%20Model%20Project/CNN%20Model%20Project%20view.png">
<img src="https://raw.githubusercontent.com/dsg890789/JwsPortfolio/master/CNN%20Model%20Project/CNN%20Model%20Project%20view-2.png">
<img src="https://raw.githubusercontent.com/dsg890789/JwsPortfolio/master/CNN%20Model%20Project/AWS%20SageMaker-3.png">

- CNN(Convolutional Neural Network) image 전체를 보는 것이 아니라 부분을 보는 것이 핵심 아이디어이다. 부분에 해당하는 것을 filter 이고 이러한 filter를 여러번 적용 시켜 하나의 레이어 형태로 만들게 되는데 이것을 convolution layer 라고 한다. 예를 들어 6개의 filter로부터 6개의 convolution layer를 얻었다고 해보자. 현재 우리의 예에서는 N=7, F=3 이기 때문에 5x5짜리 convolution layer를 6개 얻게 되었다. 이를 하나로 합치면 5x5x6짜리 convolution layers를 얻게 되고 이렇게 activation maps까지 얻는 일련의 과정을 convolution이라고 한다. 이런 convolution을 수십 수백번 반복하여 일정하여 모델을 만들어 활용 하는 것을 CNN 알고리즘이라고 한다.
- 이번 프로젝트는 Tensorflow CNN 모델을 통하여 구성이 되었으며 연산 시간은 4vCPU 16GB의 ml.m5.xlarge 머신러닝 인스턴스를 통하여 학습 하였으며 1 에포크당 약 40분이 소요됩니다. 먼저 5 에포크의 연산을 하여 문제가 없음을 확인하였고 50 에포크를 진행 중이다.

이미지 데이터셋
-------------------------------------------------------------------------------------------
<img src="https://raw.githubusercontent.com/dsg890789/JwsPortfolio/master/CNN%20Model%20Project/AWS%20SageMaker%20S3.png">

- AWS SageMaker를 사용하여 데이터를 처리함으로 Auto Scailing의 이점을 최대한 활용하고 싶었다. 그 결과 Kaggle 에서 1.2 Gb의 동물 이미지 데이터를 구하였다.
  
> https://www.kaggle.com/alessiocorrado99/animals10

프로젝트 아키텍처
-------------------------------------------------------------------------------------------
<img src="https://raw.githubusercontent.com/dsg890789/JwsPortfolio/master/CNN%20Model%20Project/CNN%20Model%20Project%20%EC%84%A4%EA%B3%84%20%ED%8C%8C%EC%9D%BC.png">

1. 액티비티 정의
-------------------------------------------------------------------------------------------
- Users : 이 AI 모델을 작동시키는 유저이다.
- AWS Console : AWS를 관리하는 종합 관리 콘솔이다.
- AWS Cloud Watch : AWS의 각 서비스 및 인스턴스의 사용량과 요금을 실시간으로 확인하는 서비스.
- SageMaker : Sage는 현자, 마법사라는 뜻을 가지고 있으며 2019년 11월 현재 17개의 머신러닝 알고리즘을 가지고 있는 뛰어난 인공지능 서비스이다.
- CNN Model : CNN 알고리즘을 이용하여 텐서플로우 및 AWS SageMaker에 내장되어 있는 알고리즘 모델.
- Auto Scailing : 오토스케일링은 클라우드 환경의 가장 기본적인 요소들 중에 하나로,
갑작스러운 트래픽 집중에 서버, 스토리지 등의 자원이 자동으로 확장하면서, 안정적인 서비스를 유지하는 것이다. 특히 머신러닝과 같은 대량의 CPU, GPU 자원이 필요할 때 무한에 가까운 컴퓨팅 자원을 사용할 수 있다.
- S3 Bucket : S3란 Simple Storage Service의 약자로 쉽게말해 파일을 저장하는 저장소입니다. AWS에 필요한 대부분의 파일은 이 S3 Bucket에 저장하여 서비스하게 됩니다. 이 프로젝트에 사용하는 목적은 머신러닝에 사용 할 데이터는 적게는 수백 메가바이트에서 많게는 페타바이트에 달하는 데이터를 저장하기엔 물리적인 드라이브로는 한계가 있다. 하지만 S3 Bucket을 이용하면 무한정에 가까운 데이터를 저장하여 머신러닝에 사용 할 수 있다.

> https://docs.aws.amazon.com/ko_kr/sagemaker/latest/dg/image-classification.html
> https://www.guru99.com/keras-tutorial.html
> https://docs.aws.amazon.com/cli/latest/reference/sagemaker/update-notebook-instance.html

1. Amazon SageMaker
-------------------------------------------------------------------------------------------
<img src="https://raw.githubusercontent.com/dsg890789/JwsPortfolio/master/CNN%20Model%20Project/AWS%20SageMaker.png">
<img src="https://raw.githubusercontent.com/dsg890789/JwsPortfolio/master/CNN%20Model%20Project/AWS%20SageMaker-2.png">

> 모든 개발자와 데이터 과학자를 위한 머신러닝.
- Amazon SageMaker는 모든 개발자와 데이터 과학자에게 기계 학습 모델을 신속하게 구축 및 학습하고 배포할 수 있는 기능을 제공합니다. Amazon SageMaker는 전체 기계 학습 워크플로를 포괄하여 데이터를 분류 및 준비하고, 알고리즘을 선택하며, 모델을 학습하고, 배포를 위해 조정 및 최적화하고, 예측을 수행하며, 작업을 수행하는 완전관리형 서비스입니다. 훨씬 적은 노력과 비용으로 더 빨리 모델을 실행할 수 있습니다. 기본 제공되는 쥬피터랩을 통해 목적에 맞는 개발환경을 클릭 몇번으로 변경하거나 구축 할 수 있다.
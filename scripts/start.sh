echo "> start 시작" >> /home/ec2-user/deploy.log

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

IDLE_PROFILE=$(find_profile)
IDLE_PORT=$(find_port)

echo "> 도커 이미지 파일을 생성합니다." >> /home/ec2-user/deploy.log
sudo docker build --build-arg IDLE_PROFILE=${IDLE_PROFILE} -f /home/ec2-user/cicd-gururu/Dockerfile -t ${IDLE_PROFILE} /home/ec2-user/cicd-gururu >> /home/ec2-user/deploy.log 2>/home/ec2-user/deploy_err.log
sleep 30

echo "> 도커 컨테이너를 실행합니다." >> /home/ec2-user/deploy.log
sudo docker run -e DB_url=${DB_URL} -e DB_username=${DB_USERNAME} -e DB_password=${DB_PASSWORD} -p ${IDLE_PORT}:${IDLE_PORT} ${IDLE_PROFILE} >> /home/ec2-user/spring.log 2>/home/ec2-user/deploy_err.log &
sleep 10

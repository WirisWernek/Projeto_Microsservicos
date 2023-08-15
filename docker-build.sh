#!/bin/zsh
echo "===|       Iniciando Build das Imagens do Projeto       |==="

echo "Buildando Microserviços de Avaliador de Crédito"
docker build -t cursoms-avaliadorcredito:1.5 ./msavaliadorcredito/

echo "Buildando Micorserviços de Cartões"
docker build -t cursoms-cartoes:1.5 ./mscartoes/

echo "Buildando Micorserviços de Clientes"
docker build -t cursoms-cliente:1.5 ./msclientes/

echo "Buildando Eureka Server"
docker build -t cursoms-eureka:1.5 ./eurekaserver/

echo "Buildando Gateway"
docker build -t cursoms-gateway:1.5 ./mscloudgateway/

echo "===|       ACABOU!!! É TETRA!!!       |==="

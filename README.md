# Spring Boot Kubernetes DevOps Demo

This project demonstrates a complete DevOps workflow for a Spring Boot application with:
- CI/CD pipeline using GitHub Actions
- Containerization with Docker
- GitOps-based deployment to Kubernetes using ArgoCD

## Architecture

```
┌─────────────┐     ┌──────────────┐     ┌──────────────┐     ┌─────────────┐
│  Developer  │────▶│   Git Repo   │────▶│GitHub Actions│────▶│Docker Image │
└─────────────┘     └──────────────┘     └──────────────┘     └─────────────┘
                           │                                         │
                           │                                         │
                           ▼                                         ▼
                    ┌──────────────┐                         ┌──────────────┐
                    │ArgoCD watches│◀────────────────────────│Image updated │
                    │  for changes │                         │  in manifest │
                    └──────────────┘                         └──────────────┘
                           │
                           │
                           ▼
                    ┌──────────────┐
                    │ Kubernetes   │
                    │   Cluster    │
                    └──────────────┘
```

## Prerequisites

- JDK 17+
- Docker
- Kubernetes cluster
- ArgoCD installed on your cluster
- GitHub account with permissions to create workflows

## Setup Instructions

### 1. Create the GitHub Repository

Create a new repository and push this code to it.

### 2. Configure GitHub Secrets

Add the following secrets to your GitHub repository:
- `KUBE_CONFIG` - Your Kubernetes config file encoded in base64

### 3. Install ArgoCD

If you haven't installed ArgoCD yet:

```bash
kubectl create namespace argocd
kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml
```

### 4. Deploy the ArgoCD Application

Update the `argocd/application.yaml` file with your repository information, then:

```bash
kubectl apply -f argocd/application.yaml
```

### 5. Test the Complete Pipeline

1. Make a change to the code
2. Push to the main branch
3. GitHub Actions will build, test, and push a new Docker image
4. The Kubernetes manifest will be updated with the new image tag
5. ArgoCD will detect the change and sync the application to the cluster

## Local Development

To run the application locally:

```bash
./mvnw spring-boot:run
```

## Building and Running with Docker

```bash
docker build -t spring-boot-app .
docker run -p 8080:8080 spring-boot-app
```

## Endpoints

- `/` - Home page
- `/hello` - Returns a greeting
- `/hello/{name}` - Returns a personalized greeting
- `/health` - Health check endpoint

## Customizing the Application

- Update `application.properties` to modify application settings
- Edit the Kubernetes manifests in the `k8s` directory to change deployment settings
- Modify the GitHub Actions workflow in `.github/workflows/ci-cd.yml`


<!-- argocd repo add https://github.com/thecodecloud007/springboot-app-k8s-argocd.git --username thecodecloud007 --password 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

kubectl delete -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml -->
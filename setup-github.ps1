#!/usr/bin/env pwsh
# Script para configurar repositório Git e GitHub

Write-Host "=== Configuração do Repositório GitHub ===" -ForegroundColor Green

# 1. Verificar se Git está instalado
Write-Host "`n1. Verificando Git..." -ForegroundColor Yellow
try {
    $gitVersion = git --version
    Write-Host "✅ Git encontrado: $gitVersion" -ForegroundColor Green
} catch {
    Write-Host "❌ Git não encontrado. Instale o Git primeiro." -ForegroundColor Red
    Write-Host "Download: https://git-scm.com/download/win" -ForegroundColor Cyan
    exit 1
}

# 2. Inicializar repositório Git
Write-Host "`n2. Inicializando repositório Git..." -ForegroundColor Yellow
try {
    git init
    Write-Host "✅ Repositório Git inicializado" -ForegroundColor Green
} catch {
    Write-Host "❌ Erro ao inicializar Git: $($_.Exception.Message)" -ForegroundColor Red
    exit 1
}

# 3. Configurar Git (se necessário)
Write-Host "`n3. Configurando Git..." -ForegroundColor Yellow
Write-Host "Execute os comandos abaixo se necessário:" -ForegroundColor Cyan
Write-Host "git config --global user.name 'Seu Nome'" -ForegroundColor White
Write-Host "git config --global user.email 'seu.email@example.com'" -ForegroundColor White

# 4. Adicionar arquivos
Write-Host "`n4. Adicionando arquivos..." -ForegroundColor Yellow
try {
    git add .
    Write-Host "✅ Arquivos adicionados ao Git" -ForegroundColor Green
} catch {
    Write-Host "❌ Erro ao adicionar arquivos: $($_.Exception.Message)" -ForegroundColor Red
    exit 1
}

# 5. Fazer commit inicial
Write-Host "`n5. Fazendo commit inicial..." -ForegroundColor Yellow
try {
    git commit -m "Initial commit: Backend API vini-Autos-api"
    Write-Host "✅ Commit inicial realizado" -ForegroundColor Green
} catch {
    Write-Host "❌ Erro ao fazer commit: $($_.Exception.Message)" -ForegroundColor Red
    exit 1
}

# 6. Instruções para GitHub
Write-Host "`n=== Próximos Passos para GitHub ===" -ForegroundColor Green
Write-Host "1. Acesse: https://github.com/new" -ForegroundColor Cyan
Write-Host "2. Nome do repositório: projeto-autopecas" -ForegroundColor White
Write-Host "3. Descrição: Sistema de gerenciamento de autopeças com Spring Boot, PostgreSQL e React" -ForegroundColor White
Write-Host "4. Marque como público" -ForegroundColor White
Write-Host "5. NÃO marque 'Add a README file'" -ForegroundColor White
Write-Host "6. Clique em 'Create repository'" -ForegroundColor White

Write-Host "`n7. Após criar o repositório, execute:" -ForegroundColor Yellow
Write-Host "git remote add origin https://github.com/SEU_USUARIO/projeto-autopecas.git" -ForegroundColor White
Write-Host "git branch -M main" -ForegroundColor White
Write-Host "git push -u origin main" -ForegroundColor White

Write-Host "`n=== Configuração Concluída ===" -ForegroundColor Green
Write-Host "Repositório local configurado com sucesso!" -ForegroundColor Green

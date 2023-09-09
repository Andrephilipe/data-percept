from django.urls import path
from . import views

urlpatterns = [
    path('itens/', views.lista_itens, name='lista_itens'),
]

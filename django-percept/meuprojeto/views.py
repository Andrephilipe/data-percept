from django.shortcuts import render
from .models import Item

def lista_itens(request):
    itens = Item.objects.all()
    return render(request, 'C:/Users/andre.p.cassiano/projeto-data-percept/data-percept/django-percept/meuprojeto/templates/lista_itens.html', {'itens': itens})

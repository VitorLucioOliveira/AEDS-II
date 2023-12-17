import ArvoreBinaria.No;

public class Questao4 {

  static int N;

  static void treeSort(int[] array) {
    ArvoreBinaria arvore = new ArvoreBinaria();
     N = 0;

    for (int i = 0; i < array.length; i++) {
      arvore.inserir(array[i]);
    }

    treeSort(arvore.raiz, array);
  }

  private static void treeSort(No i, int[] array) { //0(n)
    if (raiz != null) {
      
      treeSort(i.esq, array);
      
      array[N++] = i.valor;
      
      treeSort(i.dir, array);
    }
  }
}

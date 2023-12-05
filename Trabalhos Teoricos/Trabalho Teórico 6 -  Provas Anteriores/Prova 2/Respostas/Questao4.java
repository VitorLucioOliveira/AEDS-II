import ArvoreBinaria.No;

public class Questao4 {

  static int N;

  static void treeSort(int[] array) {
    ArvoreBinaria arvore = new ArvoreBinaria();
    int static pos = 0;

    for (int i = 0; i < array.length; i++) {
      arvore.inserir(array[i]);
    }

    treeSort(raiz, array);
  }

  private static void treeSort(No i, int[] array) { //0(n)
    if (raiz != null) {
      
      treeSort(i.esq, array);
      
      array[Questao4.N++] = i.valor;
      
      treeSort(i.dir, array);
    }
  }
}

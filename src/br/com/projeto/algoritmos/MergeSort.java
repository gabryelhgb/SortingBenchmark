package br.com.projeto.algoritmos;

/**
 * Implementação do Merge Sort (Ordenação por Mesclagem).
 * Complexidade: O(n log n) em todos os casos.
 */
public class MergeSort implements AlgoritmoDeOrdenacao {
    
    @Override
    public void ordenar(int[] vetor) {
        mergeSort(vetor, 0, vetor.length - 1);
    }

    /**
     * Método recursivo que divide o vetor.
     */
    private void mergeSort(int[] vetor, int esq, int dir) { // 'l' -> 'esq', 'r' -> 'dir'
        if (esq < dir) {
            // Encontra o meio
            int meio = (esq + dir) / 2; // 'm' -> 'meio'
            
            // Ordena a primeira e a segunda metade
            mergeSort(vetor, esq, meio);
            mergeSort(vetor, meio + 1, dir);
            
            // Junta (mescla) as duas metades
            mesclar(vetor, esq, meio, dir); // 'merge' -> 'mesclar'
        }
    }

    /**
     * Método que junta (mescla) duas sub-arrays.
     */
    private void mesclar(int[] vetor, int esq, int meio, int dir) {
        // Tamanhos das duas sub-arrays
        int tam1 = meio - esq + 1; // 'n1' -> 'tam1'
        int tam2 = dir - meio;     // 'n2' -> 'tam2'

        // Cria arrays temporários
        int[] VetorEsq = new int[tam1]; // 'L' -> 'VetorEsq'
        int[] VetorDir = new int[tam2]; // 'R' -> 'VetorDir'

        // Copia os dados para os arrays temporários
        System.arraycopy(vetor, esq, VetorEsq, 0, tam1);
        System.arraycopy(vetor, meio + 1, VetorDir, 0, tam2);

        // Índices iniciais
        int i = 0, j = 0;
        int k = esq;
        
        // Mescla as arrays temporárias de volta na array principal
        while (i < tam1 && j < tam2) {
            if (VetorEsq[i] <= VetorDir[j]) {
                vetor[k] = VetorEsq[i];
                i++;
            } else {
                vetor[k] = VetorDir[j];
                j++;
            }
            k++;
        }

        // Copia os elementos restantes de VetorEsq[], se houver
        while (i < tam1) {
            vetor[k] = VetorEsq[i];
            i++;
            k++;
        }
        
        // Copia os elementos restantes de VetorDir[], se houver
        while (j < tam2) {
            vetor[k] = VetorDir[j];
            j++;
            k++;
        }
    }
}
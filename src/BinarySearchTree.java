import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {

    private static final class Node {

        public Node father;
        public Node left;
        public Node right;
        public LocalidadeFaixa element;

        public Node(LocalidadeFaixa element) {
            father = null;
            left = null;
            right = null;
            this.element = element;
        }
    }

    // Atributos
    private int count; // contagem do número de nodos
    private Node root; // referência para o nodo raiz

    public BinarySearchTree() {
        count = 0;
        root = null;
    }

    public void clear() {
        count = 0;
        root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public int size() {
        return count;
    }

    public LocalidadeFaixa getRoot() {
        if (isEmpty()) {
            throw new RuntimeException();
        }
        return root.element;
    }

    public void add(LocalidadeFaixa element) {
        root = add(root, element, null);
        count++;
    }

    private Node add(Node n, LocalidadeFaixa element, Node father){
        if(n == null){
            Node aux = new Node(element);
            aux.father = father;
            return aux;
        }

        if(n.element.compareTo(element) > 0){
            n.left = add(n.left, element, n);
        }else{
            n.right = add(n.right, element, n);
        }

        return n;
    }

    public LinkedList<LocalidadeFaixa> positionsPre() {
        LinkedList<LocalidadeFaixa> res = new LinkedList<LocalidadeFaixa>();
        positionsPreAux(root, res);
        return res;
    }

    private void positionsPreAux(Node n, LinkedList<LocalidadeFaixa> res) {
        if (n != null) {
            res.add(n.element); // Visita o nodo
            positionsPreAux(n.left, res); // Visita a subárvore da esquerda
            positionsPreAux(n.right, res); // Visita a subárvore da direita
        }
    }

    public LinkedList<LocalidadeFaixa> positionsPos() {
        LinkedList<LocalidadeFaixa> res = new LinkedList<LocalidadeFaixa>();
        positionsPosAux(root, res);
        return res;
    }

    private void positionsPosAux(Node n, LinkedList<LocalidadeFaixa> res) {
        if (n != null) {
            positionsPosAux(n.left, res); // Visita a subárvore da esquerda
            positionsPosAux(n.right, res); // Visita a subárvore da direita
            res.add(n.element); // Visita o nodo
        }
    }

    public LinkedList<LocalidadeFaixa> positionsCentral() {
        LinkedList<LocalidadeFaixa> res = new LinkedList<LocalidadeFaixa>();
        positionsCentralAux(root, res);
        return res;
    }

    private void positionsCentralAux(Node n, LinkedList<LocalidadeFaixa> res) {
        if (n != null) {
            positionsCentralAux(n.left, res); // Visita a subárvore da esquerda
            res.add(n.element); // Visita o nodo
            positionsCentralAux(n.right, res); // Visita a subárvore da direita
        }
    }

    public LinkedList<LocalidadeFaixa> positionsWidth() {
        Queue<Node> fila = new LinkedList<>();
        Node atual = null;
        LinkedList<LocalidadeFaixa> res = new LinkedList<LocalidadeFaixa>();
        if (root != null) {
            fila.add(root);
            while (!fila.isEmpty()) {
                atual = fila.poll();
                if (atual.left != null) {
                    fila.add(atual.left);
                }
                if (atual.right != null) {
                    fila.add(atual.right);
                }
                res.add(atual.element);
            }
        }
        return res;
    }

    public String strTraversalCentral() {
        return strTraversalCentral(root);
    }

    private String strTraversalCentral(Node n) {
        return null;
    }

    public boolean contains(LocalidadeFaixa element) {
        Node aux = searchNodeRef(element, root);
        return aux != null;
    }

    private Node searchNodeRef(LocalidadeFaixa element, Node target) {
        if(element == null || target == null){
            return null;
        }
        int aux = element.compareTo(target.element);
        if(aux == 0){
            return target;
        }else if(aux < 0){
            return searchNodeRef(element, target.left);
        }else{
            return searchNodeRef(element, target.right);
        }
    }

    public boolean remove(LocalidadeFaixa element) {
        Node removendo = searchNodeRef(element, root);
        if(removendo == null)return false;
        if(removendo == root){
            root = null;
            return true;
        }

        Node father = removendo.father;
        Node side = null;

        if(removendo.left != null && removendo.right != null){
            side = removendo.left;
            side.father = father;
            side.right = removendo.right;
            side.right.father = side;
        }else if (removendo.left != null){
            side = removendo.left;
            side.father = father;
        }else if(removendo.right != null){
            side = removendo.right;
            side.father = father;
        }else{
            side = null;
        }

        if(father.left == removendo)
            father.left = side;
        else{
            father.right = side;
        }

        count--;
        return true;
    }

    private Node smallest(Node n) {
        Node aux = n;
        while(aux.left != null){
            aux = aux.left;
        }
        return aux;
    }

    public Integer set(Integer old, Integer element) {
        return null;
    }

    public boolean isExternal(int element) {
        return false;
    }

    public boolean isInternal(int element) {
        return false;
    }

    public LocalidadeFaixa get(int cep){
        Node atual = root;
        if(atual == null)return null;
        if(cep == root.element.getLocCepIni()){
            return atual.element;
        }

        while(atual != null){
            if(atual.element.getLocCepIni() == cep)return atual.element;
            if(atual.element.getLocCepIni() > cep ){
                atual = atual.left;
            }else{
                atual = atual.right;
            }
        }
        return null;
    }
}

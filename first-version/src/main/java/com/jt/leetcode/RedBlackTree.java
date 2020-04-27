package com.jt.leetcode;

/**
 * @author: jingteng
 * @date: 2020/4/27 20:05
 * 1、创建红黑树RBTree，定义颜色
 * 2、创建RBNode
 * 3、辅助方法定义：parentOf(node),isRed(node)，isBlack(node),setRed(node),setBlack(node),inOrderPrint()
 * 4、左旋方法：leftRotate(node)
 * 5、右旋方法：rightRotate(node)
 * 6、公开插入接口方法：insert(K key，V value)
 * 7、内部插入接口方法：insert(RBNode node)
 * 8、修正插入导致红黑树失衡的方法：insertFixup(RBNode node)
 * 9、测试
 */
public class RedBlackTree<K extends Comparable<K>,V> {

    //1
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    /**
     * 树根的引用
     * */
    private RBNode root;

    //2
    static class  RBNode<K extends Comparable<K> ,V>{
        private RBNode parent;
        private RBNode left;
        private RBNode right;
        private boolean color;
        private K key;
        private V value;

        public RBNode() {
        }

        public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, K key, V value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.key = key;
            this.value = value;
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    /**
     * 3.1  parentOf(node) 获取当前节点的父节点
     * */
    private RBNode parentOf(RBNode node){
        if (node != null) {
            return node.parent;
        }
        return null;
    }

    /**
     * 3.2 isRed(node) 节点是否为红色
     *
     * */
    private boolean isRed(RBNode node){
        if (node != null) {
            return node.color == RED;
        }
        return false;
    }

    /**
     * 3.3 isBlack(node) 节点是否为黑色
     *
     * */
    private boolean isBlack(RBNode node){
        if (node != null) {
            return node.color == BLACK;
        }
        return false;
    }

    /**
     * 3.4 setRed(node) 设置节点为红色
     *
     * */
    private void setRed(RBNode node){
        if (node != null) {
            node.color = RED;
        }
    }

    /**
     * 3.5 setBlack(node) 设置节点为黑色,
     *
     * */
    private void setBlack(RBNode node){
        if (node != null) {
            node.color = BLACK;
        }
    }

    /**
     * 3.6 inOrderPrint() 中序打印二叉树
     * */
    public void inOrderPrint(){
        inOrderPrint(this.root);
    }

    private void inOrderPrint(RBNode node){
        if (node != null) {
            inOrderPrint(node.left);
            StringBuilder result = new StringBuilder("key: " + node.key + ", value : " + node.value + " , color : " + node.color + ",parent: ");
            if (node.parent != null) {
                result.append(node.parent.key);
                /*if (node.parent.left != null){
                    result.append(" , left child: " + node.parent.left.key);
                }
                if (node.parent.right != null){
                    result.append(" , left child: " + node.parent.right.key);
                }*/
                if (node == node.parent.left){
                    result.append(" 左");
                }else {
                    result.append(" 右");
                }
            }
            System.out.println(result);
            inOrderPrint(node.right);
        }
    }

    /**
     * 4 左旋：以 x 为支点
     *      P                           P
     *      |                           |
     *      x                           y
     *     | \       ---->             | \
     *    lx  y                       x  ry
     *       | \                     | \
     *      ly  ry                  lx  ly
     *
     *     1、处理 y 的左子节点：将 x 的右子节点指向 y 的左子节点, 并将 y 的左子节点的父节点更新为x，
     *     2、处理 x 的父节点：当 x 的父节点不为空时，更新 y 的父节点为 x 的父节点，将 x 的父节点指向 y
     *     3、更新 x 的父节点为 y ,和 y 的左节点为 x
     *
     * 注意顺序：
     * 指定某个节点时，注意左右
     *
     * */
    private void leftRotate(RBNode x){
        RBNode y = x.right;
        //1
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }

        //2
        if (x.parent != null) {
            y.parent = x.parent;
            if (x == x.parent.left){//说明x之前在其父节点P的左子节点
                x.parent.left = y;
            }else {
                x.parent.right = y;
            }
        }else {//x没有父节点，说明x是根节点
            this.root = y;
            this.root.parent = null;
        }

        //3
        x.parent = y;
        y.left = x;
    }

    /**
     * 5 右旋：以 y 为支点
     *
     *      P                           P
     *      |                           |
     *      x                           y
     *     | \       <----             | \
     *    lx  y                       x  ry
     *       | \                     | \
     *      ly  ry                  lx  ly
     *
     *
     *
     *      1、处理 x 的右子节点：将 y 的左子节点指向 x 的右子节点, 并将 x 的右子节点的父节点更新为y，
     *      2、处理 y 的父节点：当 y 的父节点不为空时，更新 x 的父节点为 y 的父节点，将 y 的父节点指向 x
     *      3、更新 y 的父节点为 x ,和 x 的右子节点为 y
     *
     * */
    private void rightRotate(RBNode y){
        RBNode x = y.left;
        //1
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }

        //2
        if (y.parent != null) {
            x.parent = y.parent;
            if (y == y.parent.left){
                y.parent.left = x;
            }else {
                y.parent.right = x;
            }
        }else {
            this.root = x;
            this.root.parent = null;
        }

        //3
        x.right = y;
        y.parent = x;
    }

    /**
     * 6、公开插入接口方法：insert(K key，V value)
     * */
    public void insert(K key,V value){
        RBNode node = new RBNode();
        node.setKey(key);
        node.setValue(value);

        //新节点一定是红色
        node.setColor(RED);
        insert(node);
    }

    /**
     * 7、内部插入接口方法：insert(RBNode node)
     *
     *      1、查找当前节点的父节点
     *      2、将该节点的父节点设置为第一步找到的父节点。
     *      3、将该节点设置到父节点的子节点（判断左右）
     *      4、插入后可能破坏了红黑树的平衡，需要修复平衡
     * */
    private void insert(RBNode node){
        //1
        RBNode parent = null;//记录当前节点的父节点
        RBNode x = this.root;//记录当前节点
         while (x != null){//如果不是第一次插入，那么会进入这个循环
             parent = x;
             int cmp = node.key.compareTo(x.key);//判断位置
             if (cmp > 0){//说明应该去右子树查询
                 x = x.right;
             }else if (cmp < 0){//说明应该去左子树查询
                 x = x.left;
             }else {//说明当前节点就是要找的值，进行替换操作，并且不再进行插入
                 x.setValue(node.value);
                 return;
             }
         }
         //2
        node.parent = parent;
         //3
        if (parent != null) {//这个数已经有了节点，正常插入
            int cmp = node.key.compareTo(parent.key);
            if (cmp > 0){//当前节点的key比父节点大，放入父节点的右子节点
                node.parent.right = node;
            }else{//这里cmp已经不可能等于0了，因为cmp=0的情况在上边已经结束方法了
                node.parent.left = node;
            }

        }else {//这棵树没有节点，将根节点设置为该节点
            this.root = node;
        }

        //4
        insertFixup(node);

    }



    /**
     * 8、修正插入导致红黑树失衡的方法：insertFixup(RBNode node)
     *
     *
     *
     *      情景1：红黑树是空树，需要将根节点染色为黑色
     *      情景2：插入节点的key存在，只是替换了值，不需要修复平衡
     *      情景3：插入节点的父节点是黑色。因为插入路径的黑色节点没有变化，不需要修复
     *      情景4：插入节点的父节点是红色：
     *                  4.1（父-叔 双红）     叔叔节点存在，并且是红色，将父、叔染黑，爷爷染红，并且以爷爷节点作为当前节点，进行下一轮处理：这个时候爷爷节点相当于新插入的节点
     *                  4.2 叔叔不存在或者是黑色，父节点是爷爷节点的左子树
     *                      4.2.1   LL双红：   将爸爸染黑，爷爷染红，以爷爷进行右旋
     *                      4.2.2   LR双红：   以爸爸进行左旋，得到 LL 双红，以爸爸节点为当前节点，重复4.2.1的处理
     *                      4.3 叔叔节点不存在或者是黑色，父节点是爷爷节点的右子树
     *                      4.3.1   RR双红：   将爸爸染黑，爷爷染红，以爷爷进行左旋
     *                      4.3.2   RL双红：   以爸爸进行右旋，得到 RR 双红，以爸爸节点作为当前节点，重复4.3.1的处理
     * */

    private void insertFixup(RBNode node){
        //1
        this.root.setColor(BLACK);

        RBNode parent = parentOf(node);
        RBNode gparent = parentOf(parent);

        //4 插入节点的父节点是红色：
        if (parent != null && isRed(parent)){
            //如果父节点是红色，那么一定存在爷爷节点，因为根节点不可能是红色，说明了父节点不是根节点

            //定义叔叔节点
            RBNode uncle = null;

            if (parent == gparent.left){//父节点是爷爷节点的左子树节点
                uncle = gparent.right;//叔叔节点是右子树节点

                //4.1 父-叔 双红
                if (uncle != null && isRed(uncle)){
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    insertFixup(gparent);
                    return;
                }
                //4.2
                if (uncle == null || isBlack(uncle)){
                    //4.2.1
                    if (node == parent.left){
                        setBlack(parent);
                        setRed(gparent);
                        rightRotate(gparent);
                        return;
                    }

                    //4.2.2
                    if (node == parent.right){
                        leftRotate(parent);
                        insertFixup(parent);
                        return;
                    }
                }

            }else {//父节点是爷爷节点的右子树节点
                uncle = gparent.left;//叔叔节点是左子树节点

                //4.1 父-叔 双红
                if (uncle != null && isRed(uncle)){
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    insertFixup(gparent);
                    return;
                }

                //4.3
                if (uncle == null || isBlack(uncle)){
                    //4.3.1
                    if (node == parent.right){
                        setBlack(parent);
                        setRed(gparent);
                        leftRotate(gparent);
                        return;
                    }

                    //4.3.2
                    if (node == parent.left){
                        rightRotate(parent);
                        insertFixup(parent);
                        return;
                    }
                }
            }

        }

    }
}















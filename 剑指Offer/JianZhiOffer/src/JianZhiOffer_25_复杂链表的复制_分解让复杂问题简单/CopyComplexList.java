package JianZhiOffer_25_复杂链表的复制_分解让复杂问题简单;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: sunnyandgood
 * @Date: 2019/7/18 23:15
 *
 * 25、复杂链表的复制
 */
public class CopyComplexList {
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) return null;
        RandomListNode head = new RandomListNode(pHead.label);
        RandomListNode ans = head;
        if (pHead.random != null) {
            head.random = new RandomListNode(pHead.random.label);
        }
        while (pHead.next != null) {
            pHead = pHead.next;
            head.next = new RandomListNode(pHead.label);
            if (pHead.random != null) {
                head.next.random = new RandomListNode(pHead.random.label);
            }
            head = head.next;
        }
        return ans;
    }

    public RandomListNode Clone1(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        // 用于建立源链表与结果链表节点对应的关系，方便结果链表 random 指针的赋值
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode rHead = new RandomListNode(pHead.label);
        map.put(pHead, rHead);
        // 建立结果链表
        RandomListNode pNode = pHead, rNode = rHead;
        while (pNode.next != null) {
            pNode = pNode.next;
            rNode.next = new RandomListNode(pNode.label);
            rNode = rNode.next;
            map.put(pNode, rNode);
        }
        // 确立结果链表 random 指针
        pNode = pHead;
        rNode = rHead;
        while (pNode != null) {
            rNode.random = map.get(pNode.random);
            pNode = pNode.next;
            rNode = rNode.next;
        }
        return rHead;
    }
}
class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
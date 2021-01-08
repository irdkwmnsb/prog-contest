package main;

public class IntQueue {
    int[] a;
    int front;
    int back;
    int capacity;
    int size;

    IntQueue() {
        back = 0;
        front = 0;
        capacity = 1;
        size = 0;
        a = new int[capacity];
    }

    int size() {
        return Integer.max(front, back) - Integer.min(front,back);
    }
// TODO
//    void vqueue_resize(int new_capacity) {
//        assert(new_capacity >= size);
//        VT *new_a = malloc(new_capacity * sizeof(VT));
//        for (size_t i = 0; i < v->size; i++) {
//            new_a[i] = v->a[v->back];
//            v->back = (v->back + 1) % v->capacity;
//        }
//        free(v->a);
//        v->a = new_a;
//        v->back = 0;
//        v->front = v->size;
//        v->capacity = new_capacity;
//    }
//
//    void vqueue_push(struct vqueue *v, VT a) {
//        ++v->size;
//        v->a[v->front] = a;
//        v->front = (v->front + 1) % v->capacity;
//        if (v->front == v->back) {
//            vqueue_resize(v, v->capacity * 2);
//        }
//    }
//
//    VT vqueue_back(struct vqueue *v) {
//#ifdef LOCAL
//        assert(v->size > 0); //top on an empty vector_init
//#endif
//        return v->a[v->back];
//    }
//
//    void vqueue_pop(struct vqueue *v) {
//#ifdef LOCAL
//        assert(v->size > 0); //pop on an empty vector_init
//#endif
//                --v->size;
//        v->back = (v->back + 1) % v->capacity;
//        if (v->size < v->capacity / 4) {
//            vqueue_resize(v, v->capacity / 2);
//        }
//    }
}

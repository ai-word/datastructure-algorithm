package main

import "fmt"

// 归并排序
// 1.将要排序的序列，从中间开始递归分解
// 2.分解到子序列只有一个元素，然后开始合并
// 3.创建一个临时序列，大小为本次需要合并的两个有序序列长度之和
// 4.设定两个下标，初始位置分别为两个有序序列的下标
// 5.比较两个小标指向的元素，将较小的元素放入临时序列，并移动下标
// 6.重复第 5 步，直到有一个下标超出序列尾部
// 7.将另一个序列中剩余的元素保存当前顺序放入临时序列中
// 8.最后将临时序列复制到原始序列对应的位置
func mergeSort(nums[] int) {
    if nums == nil {
        return
    }
    decompose(nums, 0, len(nums) - 1)
}

// 分解
// start: 开始下标
// end: 结束下标
func decompose(nums[] int, start, end int) {
    if start < end {
        mid := (start + end) / 2
        decompose(nums, start, mid) // 左边
        decompose(nums, mid + 1, end) // 右边
        merge(nums, start, mid, end)
    }
}

// 合并
// left: 左边有序序列起始下标
// mid: 中间下标
// right: 右边有序序列起始下标
func merge(nums[] int, left, mid, right int) {
    temp := make([]int, right - left + 1) // 临时保存元素的数组
    i := left // 左边有序序列的起始下标
    j := mid + 1 // 右边有序列表的起始下标
    t := 0 // temp 当前下标
    // 先把左右两边有序列表中的数据，取出来比较，然后将较小的元素放入 temp 中
    for i <= mid && j <= right {
        if nums[i] <= nums[j] {
            temp[t] = nums[i]
            i++
        } else {
            temp[t] = nums[j]
            j++
        }
        t++
    }

    // 将剩余的左边或右边有序序列中的元素全部放入 temp 中
    for i <= mid { // 左边序列还有元素
        temp[t] = nums[i]
        i++
        t++
    }
    for j <= right { // 右边序列还有元素
        temp[t] = nums[j]
        j++
        t++
    }

    // 将 temp 数组中的元素拷贝到 nums 中
    t = 0
    for left <= right {
        nums[left] = temp[t]
        left++
        t++
    }
}

func main() {
    nums := []int{5, 0, 1, 7, 3, 2, 4, 9, 6, 8}
    fmt.Printf("排序前: %v\n", nums)
    mergeSort(nums)
    fmt.Printf("排序后: %v\n", nums)
}

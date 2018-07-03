package com.htkapp.core.enums;

/**
 * 执行结果状态 Enum
 * -3执行成功 查询结果为空, -2参数错误，-1操作失败，0操作成功 查询到数据,
 * 1成功刷新当前页，2成功并跳转到url，3成功并刷新iframe的父界面
 */
public enum ResultStatEnum {

    //查询为空
    QUERYEMPTY(-3, "结果是空"),
    //查询有值
    QUERYDATA(0,"结果有值");



    private int state;

    private String stateInfo;

    ResultStatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }


    /**
     * @param index
     * @return  传入code 返回对象数据
     */
    public static ResultStatEnum stateOf(int index) {
        for (ResultStatEnum state : values()) {
            //遍历取每一个和传入的index比较
            if (state.getState() == index) {
                //返回当前的遍历对象
                return state;
            }
        }
        //否则返回null
        return null;
    }
}

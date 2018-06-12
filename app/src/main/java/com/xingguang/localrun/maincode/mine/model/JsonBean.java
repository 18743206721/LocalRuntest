package com.xingguang.localrun.maincode.mine.model;


import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * Created by DELL on 2017/6/26.
 */

public class JsonBean implements IPickerViewData {
    /**
     * id : 1
     * pid : 0
     * name : 北京
     * level : 1
     * child : [{"id":"2","pid":"1","name":"密云区","level":"2","child":[{"id":"3","pid":"2","name":"城区","level":"3"},{"id":"4","pid":"2","name":"城区以外","level":"3"}]},{"id":"5","pid":"1","name":"朝阳区","level":"2","child":[{"id":"6","pid":"5","name":"三环到四环之间","level":"3"},{"id":"7","pid":"5","name":"四环到五环之间","level":"3"},{"id":"8","pid":"5","name":"五环到六环之间","level":"3"},{"id":"9","pid":"5","name":"管庄","level":"3"},{"id":"10","pid":"5","name":"北苑","level":"3"},{"id":"11","pid":"5","name":"定福庄","level":"3"},{"id":"12","pid":"5","name":"三环以内","level":"3"}]},{"id":"13","pid":"1","name":"昌平区","level":"2","child":[{"id":"14","pid":"13","name":"六环以内","level":"3"},{"id":"15","pid":"13","name":"城区","level":"3"},{"id":"16","pid":"13","name":"城区以外","level":"3"}]},{"id":"17","pid":"1","name":"平谷区","level":"2","child":[{"id":"18","pid":"17","name":"城区","level":"3"},{"id":"19","pid":"17","name":"城区以外","level":"3"}]},{"id":"20","pid":"1","name":"海淀区","level":"2","child":[{"id":"21","pid":"20","name":"三环以内","level":"3"},{"id":"22","pid":"20","name":"三环到四环之间","level":"3"},{"id":"23","pid":"20","name":"四环到五环之间","level":"3"},{"id":"24","pid":"20","name":"五环到六环之间","level":"3"},{"id":"25","pid":"20","name":"六环以外","level":"3"},{"id":"26","pid":"20","name":"西三旗","level":"3"},{"id":"27","pid":"20","name":"西二旗","level":"3"}]},{"id":"28","pid":"1","name":"西城区","level":"2","child":[{"id":"29","pid":"28","name":"内环到二环里","level":"3"},{"id":"30","pid":"28","name":"二环到三环","level":"3"}]},{"id":"31","pid":"1","name":"东城区","level":"2","child":[{"id":"32","pid":"31","name":"内环到三环里","level":"3"}]},{"id":"33","pid":"1","name":"崇文区","level":"2","child":[{"id":"34","pid":"33","name":"一环到二环","level":"3"},{"id":"35","pid":"33","name":"二环到三环","level":"3"}]},{"id":"36","pid":"1","name":"宣武区","level":"2","child":[{"id":"37","pid":"36","name":"内环到三环里","level":"3"}]},{"id":"38","pid":"1","name":"丰台区","level":"2","child":[{"id":"39","pid":"38","name":"四环到五环之间","level":"3"},{"id":"40","pid":"38","name":"二环到三环","level":"3"},{"id":"41","pid":"38","name":"三环到四环之间","level":"3"},{"id":"42","pid":"38","name":"五环到六环之间","level":"3"},{"id":"43","pid":"38","name":"六环之外","level":"3"}]},{"id":"44","pid":"1","name":"石景山区","level":"2","child":[{"id":"45","pid":"44","name":"四环到五环内","level":"3"},{"id":"46","pid":"44","name":"石景山城区","level":"3"},{"id":"47","pid":"44","name":"八大处科技园区","level":"3"}]},{"id":"48","pid":"1","name":"门头沟","level":"2","child":[{"id":"49","pid":"48","name":"城区","level":"3"},{"id":"50","pid":"48","name":"龙泉镇","level":"3"},{"id":"51","pid":"48","name":"永定镇","level":"3"},{"id":"52","pid":"48","name":"大台镇","level":"3"},{"id":"53","pid":"48","name":"潭柘寺镇","level":"3"},{"id":"54","pid":"48","name":"王平镇","level":"3"},{"id":"55","pid":"48","name":"军庄镇","level":"3"},{"id":"56","pid":"48","name":"妙峰山镇","level":"3"},{"id":"57","pid":"48","name":"雁翅镇","level":"3"},{"id":"58","pid":"48","name":"斋堂镇","level":"3"},{"id":"59","pid":"48","name":"清水镇","level":"3"}]},{"id":"60","pid":"1","name":"房山区","level":"2","child":[{"id":"61","pid":"60","name":"城区","level":"3"},{"id":"62","pid":"60","name":"大安山乡","level":"3"},{"id":"63","pid":"60","name":"大石窝镇","level":"3"},{"id":"64","pid":"60","name":"窦店镇","level":"3"},{"id":"65","pid":"60","name":"佛子庄乡","level":"3"},{"id":"66","pid":"60","name":"韩村河镇","level":"3"},{"id":"67","pid":"60","name":"河北镇","level":"3"},{"id":"68","pid":"60","name":"良乡镇","level":"3"},{"id":"69","pid":"60","name":"琉璃河镇","level":"3"},{"id":"70","pid":"60","name":"南窖乡","level":"3"},{"id":"71","pid":"60","name":"蒲洼乡","level":"3"},{"id":"72","pid":"60","name":"青龙湖镇","level":"3"},{"id":"73","pid":"60","name":"十渡镇","level":"3"},{"id":"74","pid":"60","name":"石楼镇","level":"3"},{"id":"75","pid":"60","name":"史家营乡","level":"3"},{"id":"76","pid":"60","name":"霞云岭乡","level":"3"},{"id":"77","pid":"60","name":"新镇","level":"3"},{"id":"78","pid":"60","name":"阎村镇","level":"3"},{"id":"79","pid":"60","name":"燕山地区","level":"3"},{"id":"80","pid":"60","name":"张坊镇","level":"3"},{"id":"81","pid":"60","name":"长沟镇","level":"3"},{"id":"82","pid":"60","name":"长阳镇","level":"3"},{"id":"83","pid":"60","name":"周口店镇","level":"3"}]},{"id":"84","pid":"1","name":"通州区","level":"2","child":[{"id":"85","pid":"84","name":"六环内（马驹桥镇）","level":"3"},{"id":"86","pid":"84","name":"中仓街道","level":"3"},{"id":"87","pid":"84","name":"新华街道","level":"3"},{"id":"88","pid":"84","name":"玉桥街道","level":"3"},{"id":"89","pid":"84","name":"北苑街道","level":"3"},{"id":"90","pid":"84","name":"六环外（马驹桥镇）","level":"3"},{"id":"91","pid":"84","name":"永顺镇","level":"3"},{"id":"92","pid":"84","name":"梨园镇","level":"3"},{"id":"93","pid":"84","name":"宋庄镇","level":"3"},{"id":"94","pid":"84","name":"漷县镇","level":"3"},{"id":"95","pid":"84","name":"张家湾镇","level":"3"},{"id":"96","pid":"84","name":"西集镇","level":"3"},{"id":"97","pid":"84","name":"永乐店镇","level":"3"},{"id":"98","pid":"84","name":"潞城镇","level":"3"},{"id":"99","pid":"84","name":"台湖镇","level":"3"},{"id":"100","pid":"84","name":"于家务乡","level":"3"},{"id":"101","pid":"84","name":"次渠镇","level":"3"}]},{"id":"102","pid":"1","name":"延庆县","level":"2","child":[{"id":"103","pid":"102","name":"延庆镇","level":"3"},{"id":"104","pid":"102","name":"城区","level":"3"},{"id":"105","pid":"102","name":"康庄镇","level":"3"},{"id":"106","pid":"102","name":"八达岭镇","level":"3"},{"id":"107","pid":"102","name":"永宁镇","level":"3"},{"id":"108","pid":"102","name":"旧县镇","level":"3"},{"id":"109","pid":"102","name":"张山营镇","level":"3"},{"id":"110","pid":"102","name":"四海镇","level":"3"},{"id":"111","pid":"102","name":"千家店镇","level":"3"},{"id":"112","pid":"102","name":"沈家营镇","level":"3"},{"id":"113","pid":"102","name":"大榆树镇","level":"3"},{"id":"114","pid":"102","name":"井庄镇","level":"3"},{"id":"115","pid":"102","name":"大庄科乡","level":"3"},{"id":"116","pid":"102","name":"刘斌堡乡","level":"3"},{"id":"117","pid":"102","name":"香营乡","level":"3"},{"id":"118","pid":"102","name":"珍珠泉乡","level":"3"}]},{"id":"119","pid":"1","name":"大兴区","level":"2","child":[{"id":"120","pid":"119","name":"四环至五环之间","level":"3"},{"id":"121","pid":"119","name":"五环至六环之间","level":"3"},{"id":"122","pid":"119","name":"六环以外","level":"3"},{"id":"123","pid":"119","name":"亦庄经济开发区","level":"3"}]},{"id":"124","pid":"1","name":"顺义区","level":"2","child":[{"id":"125","pid":"124","name":"北石槽镇","level":"3"},{"id":"126","pid":"124","name":"北务镇","level":"3"},{"id":"127","pid":"124","name":"北小营镇","level":"3"},{"id":"128","pid":"124","name":"大孙各庄镇","level":"3"},{"id":"129","pid":"124","name":"高丽营镇","level":"3"},{"id":"130","pid":"124","name":"光明街道","level":"3"},{"id":"131","pid":"124","name":"后沙峪地区","level":"3"},{"id":"132","pid":"124","name":"空港街道","level":"3"},{"id":"133","pid":"124","name":"李桥镇","level":"3"},{"id":"134","pid":"124","name":"李遂镇","level":"3"},{"id":"135","pid":"124","name":"龙湾屯镇","level":"3"},{"id":"136","pid":"124","name":"马坡地区","level":"3"},{"id":"137","pid":"124","name":"木林镇","level":"3"},{"id":"138","pid":"124","name":"南彩镇","level":"3"},{"id":"139","pid":"124","name":"南法信地区","level":"3"},{"id":"140","pid":"124","name":"牛栏山地区","level":"3"},{"id":"141","pid":"124","name":"仁和地区","level":"3"},{"id":"142","pid":"124","name":"胜利街道","level":"3"},{"id":"143","pid":"124","name":"石园街道","level":"3"},{"id":"144","pid":"124","name":"双丰街道","level":"3"},{"id":"145","pid":"124","name":"天竺地区","level":"3"},{"id":"146","pid":"124","name":"旺泉街道","level":"3"},{"id":"147","pid":"124","name":"杨镇地区","level":"3"},{"id":"148","pid":"124","name":"张镇","level":"3"},{"id":"149","pid":"124","name":"赵全营镇","level":"3"}]},{"id":"150","pid":"1","name":"怀柔区","level":"2","child":[{"id":"151","pid":"150","name":"城区以内","level":"3"},{"id":"152","pid":"150","name":"郊区","level":"3"}]}]
     */

    private String id;
    private String pid;
    private String name;
    private String level;
    private List<ChildBeanX> child;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<ChildBeanX> getChild() {
        return child;
    }

    public void setChild(List<ChildBeanX> child) {
        this.child = child;
    }

    @Override
    public String getPickerViewText() {
        return this.name;
    }

    public static class ChildBeanX {
        /**
         * id : 2
         * pid : 1
         * name : 密云区
         * level : 2
         * child : [{"id":"3","pid":"2","name":"城区","level":"3"},{"id":"4","pid":"2","name":"城区以外","level":"3"}]
         */

        private String id;
        private String pid;
        private String name;
        private String level;
        private List<ChildBean> child;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public List<ChildBean> getChild() {
            return child;
        }

        public void setChild(List<ChildBean> child) {
            this.child = child;
        }

        public static class ChildBean {
            /**
             * id : 3
             * pid : 2
             * name : 城区
             * level : 3
             */

            private String id;
            private String pid;
            private String name;
            private String level;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }
        }
    }



}

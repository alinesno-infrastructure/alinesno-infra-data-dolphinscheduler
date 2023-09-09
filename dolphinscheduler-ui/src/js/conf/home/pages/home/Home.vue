<template>
  <div class="home">
    <div class="homeHeader" data-spm="home-header">
        <div class="greeting" style="padding-bottom: 20px;">
            <h1>基于Dag的离线数据计算服务，提供一站式提供数据离线/批量开发处理，构建和提升数据治理水平，构建质量可靠.
              <span style="color: #0089ff;font-weight: 500;cursor: pointer;font-size: 13px;"><i class="el-icon-link"></i> 
                <a href="#" target="_blank">
                  怎么使用设计器.
                </a>
              </span> 
            </h1>
        </div>
    </div>
    <el-container>
      <el-main style="padding-top:12px">
        <el-row :gutter="20" style="margin-bottom:10px !important">
          <el-col :span="24">
            <h3 class="h3-title">
              <span tooltipicon="help" class="nb-rc-text product-title">
                <span class="the-text">我的项目</span>
              </span>
            </h3>

            <my-application />
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="24">
            <div class="WidgetHomeProductMy-detailContainer-3W8nd">
              <h3 class="h3-title">
                <span tooltipicon="help" class="nb-rc-text product-title">
                  <span class="the-text">数据开发</span>
                </span>
              </h3>
              <div style="position: relative;overflow: hidden;width: 100%;height: 220px;">
                  <ul class="product_list thm-uglier">
                    <li
                      class="product_list_li"
                      v-for="(item, index) in learnDocumentArr"
                      :key="index"
                    >                
                      <div class="text-icons">
                        <i :class="item.icon"></i>
                      </div>
                      <div class="text-title">
                        <a :href="item.link" target="_blank">
                          {{ item.title }}
                        </a>
                        <div class="text-descript">
                          {{ item.desc }}
                        </div> 
                      </div>
                    </li>
                  </ul>
              </div>
            </div>
          </el-col>
        </el-row>
      </el-main>
      <el-aside width="20%">
        <aside-count />
      </el-aside>
    </el-container>
  </div>
</template>

<script>
import MyApplication from "./myApplication.vue";
import AsideCount from "./asideCount.vue";

export default {
  name: "index",
  components: {
    MyApplication,
    AsideCount,
  },
  data() {
    return {
      learnDocumentArr: [
        {
          title: "数据维度分析",
          link: "http://alinesno-platform.linesno.com/data/framework/04_%E6%95%B0%E6%8D%AE%E5%90%8C%E6%AD%A5/02_%E7%BB%B4%E5%BA%A6%E5%BB%BA%E6%A8%A1.html",
          icon: "fas fa-file-word" , 
          status: "0",
          desc: "支持多种环境的发布，最小依赖，支持多种发布场景",
        },
        {
          title: "数据库源配置",
          link: "#",
          icon: "fab fa-hornbill" , 
          status: "0",
          desc: "服务都是基于同一套框架和代码结构",
        },
        {
          title: "离线数据开发",
          link: "http://alinesno-platform.linesno.com/data/framework/05_%E6%95%B0%E6%8D%AE%E5%88%86%E6%9E%90/02_%E5%AE%9E%E6%97%B6%E8%AE%A1%E7%AE%97.html",
          icon: "fas fa-pencil-ruler" , 
          status: "0",
          desc: "自动生成基础的CURD工程，一健生成后台",
        },
        {
          title: "流程任务配置",
          link: "#",
          icon: "fas fa-rocket" , 
          status: "0",
          desc: "自动集成基础的权限认证后台，集成默认的多种功能",
        },
        {
          title: "数据分层设计",
          link: "http://alinesno-platform.linesno.com/data/framework/02_%E6%95%B0%E6%8D%AE%E4%BB%93%E5%BA%93/02_%E6%95%B0%E4%BB%93%E5%88%86%E5%B1%82.html",
          icon: "fas fa-server" , 
          status: "0",
          desc: "一键集成容器，快速支持云原生，无缝接入平台",
        },
        {
          title: "计算数据入仓",
          link: "#",
          icon: "fab fa-hornbill" , 
          status: "0",
          desc: "数据计算结果发送到数据仓库中",
        },
      ],
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
  },
};
</script>

<style scoped lang="scss">

.homeHeader {
  position: relative;
  box-sizing: border-box;
  padding: 24px 24px 0;
  background-color: #fff;
  box-shadow: 0 1px 0 0 #e3e4e6;
  z-index: 1;
}

.homeHeader h1 {
  margin: 0 0 8px;
  font-size: 15px;
  color: #333;
  line-height: 24px;
}

.app-container {
  background-color: #fafafa;
}

.widget-title {
  font-size: 13px;
  color: #333;
  line-height: 24px;
  font-weight: 400;
  width: 100%;
}

.app-icon {
  width: 32px;
  height: 32px;
  position: relative;
}

.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}

.el-col {
  border-radius: 2px;
  background: #fff;
  padding: 20px !important ;
  padding-left: 20px !important ;
  padding-right: 20px !important ;
  border: var(--card-border-width, 1px) var(--card-border-style, solid)
    var(--card-border-color, #e3e4e6);
}

.el-col-8 {
  width: calc(33.3333333333% - 10px);
  margin-left: 10px;
}

.widget-bulletin-list .item {
  height: 32px;
  line-height: 32px;
  white-space: nowrap;
  -o-text-overflow: ellipsis;
  text-overflow: ellipsis;
  overflow: hidden;
  font-size: 12px;

  a {
    color: #555;
  }
}

.product_list_li:nth-child(3),
.product_list_li:nth-child(6) {
  border-right: 0px;
}

.product_list_li {
  width: calc(33% - 15px) !important;
  padding: 15px !important ;
  background: #f6f7f8 ;
  margin-right: 10px;
  border-radius: 15px;
  margin-top: 15px;

  .text-title {
    font-size: 16px;
    color: #333;
    letter-spacing: 0;
    line-height: 25px;
    // margin-top:10px;
    float: left;
    font-weight: 450 !important;
    width:calc(100% - 70px);
  }

  .text-descript {
    opacity: 0.7;
    font-size: 12px;
    color: #333;
    letter-spacing: 0.37px;
    line-height: 20px;
    overflow: hidden; //超出的文本隐藏
    text-overflow: ellipsis; //溢出用省略号显示
    white-space: nowrap;  // 默认不换行；
  }
}


.widget-bulletin-list {
  .line {
    position: absolute;
    left: -16px;
    right: -16px;
    height: 1px;
    background-color: #e3e4e6;
  }
  .pin {
    position: relative;
    margin-bottom: 15px;
    margin-top: 15px;

    .msg {
      padding: 8px 12px;
      line-height: 18px;
      color: #333;
      font-size: 12px;
      background-color: #eff3f8;
      -webkit-border-radius: 2px;
      -moz-border-radius: 2px;
      border-radius: 2px;
      margin-bottom: 16px;
    }
  }
}

.bg-purple-dark {
  background: #99a9bf;
}

.bg-purple {
  background: #d3dce6;
}

.bg-purple-light {
  background: #e5e9f2;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}

.next-col-4 {
  -ms-flex: 0 0 16.66667%;
  -webkit-flex: 0 0 16.66667%;
  flex: 0 0 16.66667%;
  width: 16.66667%;
  max-width: 16.66667%;
  float: left;
}

.subtitle {
  font-weight: 500;
  font-size: 12px;
  color: #666;
  line-height: 20px;
  margin: 16px 0 8px;
}

.thm-uglier {
  list-style: none;
  margin: 0;
  padding: 0;
}

.product_list {
  a {
    display: flex;
    align-items: center;
    width: 100%;
    text-decoration: none;
    color: #333;
    transition: all 250ms linear;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
}

.product_list > li {
  padding: 0 8px 8px 0;
  overflow: hidden;
  flex: none;
  width: 25%;
  float: left;
  border-radius: 4px;
  font-size: 12px;

  a {
    i {
      display: none;
      border: 1px solid #dedede;
      border-radius: 14px;
      width: 28px;
      height: 28px;
      text-align: center;
    }
  }

  .WidgetHomeProductMy-product_name-TNtW6 {
    flex: 1;
    margin-left: 8px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
}

.h3-title {
  font-size: 17px;
  font-weight: 600;
  margin-top: 0px;
  margin-bottom: 10px;
  // padding-left: 16px;
}

.text-icons {
  float: right;
  // margin-right: 10px;
  font-size: 16px;
  font-weight: 200;
  color: #ccc;
}

.text-icons {
  margin-right: 10px;
  font-weight: 200;
  float: left;
  height: 45px;
  font-size: 24px;
  background: #005bd5 ; 
  width: 45px;
  text-align: center;
  padding-top: 6px;
  border-radius: 3px;
  color: #fff;
  box-shadow: 0 4px 12px -2px rgba(82,80,255,.58) ; 
}


li.product_list_li.count-li {
  width: calc(50% - 16px) !important;
  height: 140px;
  border-right: 0px;
  border-radius: 5px !important;
  margin: 0px 0px 16px 16px !important;
}

.count-text-button > button {
  padding: 0 16px;
  height: 32px;
  line-height: 30px;
  font-size: 12px;
  border-width: 1px;
  border-style: solid;
  background-color: #005bd4;
  border-color: transparent;
  margin-top: 20px;
  color: #fff;
  border-radius: 3px;
}

.el-aside {
  background: #fff !important ;
  margin-top: 12px !important;
  padding-left: 0px;
  padding-right: 10px;
}
</style>



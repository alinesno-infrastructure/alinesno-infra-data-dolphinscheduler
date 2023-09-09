<!-- 统计服务 -->
<template>
  <div>
    <!-- 运行状态 -->
    <div class="data-card">
      <div class="title">运行工作台</div>
      <ul class="count-list">
        <li class="count-data">
          <span class="label-tip">运行项目数</span>
          <span class="label">{{ countData.appCount }}</span>
        </li>
        <li class="count-data">
          <span class="label-tip">运行任务数</span>
          <span class="label">{{ countData.databaseCount }}</span>
        </li>
        <li class="count-data">
          <span class="label-tip">成功任务</span>
          <span class="label">{{ countData.databaseCount }}</span>
        </li>
        <li class="count-data">
          <span class="label-tip">失败任务</span>
          <span class="label">{{ countData.databaseCount }}</span>
        </li>
      </ul>
    </div>

    <!-- 权限设置 -->
    <div class="rbac-data-card">
      <div class="title">数据开发</div>
      <img class="data-design-img" :src="require('./source/data_design.png')" />
      
      <ul class="quickEntry">
        <li v-for="(item , index) in documentArr" :key="index">
          <a :href="item.link" target="blank" class="v3-4-29-btn v3-4-29-medium v3-4-29-btn-normal">
            <span class="v3-4-29-btn-helper"> <i class="el-icon-link"></i>  {{ item.name }}</span>
          </a>
        </li>
      </ul>

    </div>
  </div>
</template>

<script>

// import { dashboardCount } from "@/api/Common" ; 

export default {
  components: {},
  data() {
    return {
      functionCount: 123,
      accountCount: 1200,
      documentArr:[
        {name:'数据架构' , link:'http://alinesno-platform.linesno.com/data/framework/'} ,
        {name:'数据开发' , link:'http://alinesno-platform.linesno.com/dataskill/'} ,
      ],
      countData: {
        appCount: 12 , 
        databaseCount: 10 , 
        genAppCount: 12 , 
        genDataCount: 87 , 
      }, 
    };
  },
  created() {
    // this.initCount() ; 
  } ,
  methods: {
    initCount(){
      dashboardCount().then(res => {
        this.countData = res.data ;
      });
    } ,
  },
};
</script>

<style scoped lang="scss">
@import './source/Sidebar.scss';
</style>



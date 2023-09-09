<!-- 统计服务 -->
<template>
  <div>
    <!-- 运行状态 -->
    <div class="data-card">
      <div class="title">运行状态</div>
      <ul class="count-list">
        <li class="count-data" v-for="(item, index) in processStateList" :key="index" v-if="item.taskStateType === 'READY_STOP' ||item.taskStateType === 'PAUSE' ||item.taskStateType === 'KILL' ||item.taskStateType === 'SUBMITTED_SUCCESS' ||item.taskStateType === 'SUCCESS' || item.taskStateType === 'FAILURE'|| item.taskStateType === 'RUNNING_EXECUTION' || item.taskStateType === 'DELAY_EXECUTION'" >
          <div class="op-li-title" v-if="item.taskStateType === 'KILL'">{{ 'KILL终止' }}</div>
          <div class="op-li-title" v-if="item.taskStateType === 'SUBMITTED_SUCCESS'">{{ '提交成功' }}</div>
          <div class="op-li-title" v-if="item.taskStateType === 'SUCCESS'">{{ '任务成功' }}</div>
          <div class="op-li-title" v-if="item.taskStateType === 'FAILURE'">{{ '任务失败' }}</div>
          <div class="op-li-title" v-if="item.taskStateType === 'RUNNING_EXECUTION'">{{ '正在运行' }}</div>
          <div class="op-li-title" v-if="item.taskStateType === 'DELAY_EXECUTION'">{{ '任务超时' }}</div>
          <div class="op-li-title" v-if="item.taskStateType === 'READY_STOP'">{{ '就绪' }}</div>
          <div class="op-li-title" v-if="item.taskStateType === 'PAUSE'">{{ '暂停' }}</div>
          <div class="op-li-number">{{ item.count }}</div>
        </li>
      </ul>
    </div>

    <!-- 项目统计 -->
    <div class="rbac-data-card">
      <div class="title">项目统计</div>
      <ul class="count-list">
        <li class="count-data">
          <span class="label-tip">项目统计</span>
          <span class="label-number">{{ DefineProjectCount }}</span>
        </li>
        <li class="count-data">
          <span class="label-tip">任务统计</span>
          <span class="label-number">{{ DefineUserCount }}</span>
        </li>
      </ul>

      <ul class="quickEntry">
        <li>
          <a title="项目管理" @click="applicationManger()" class="v3-4-29-btn v3-4-29-medium v3-4-29-btn-normal"><span
              class="v3-4-29-btn-helper"><i class="el-icon-link"></i> 项目管理</span></a>
        </li>
        <li>
          <a title="运行管理" @click="runManger()" class="v3-4-29-btn v3-4-29-medium v3-4-29-btn-normal"><span
              class="v3-4-29-btn-helper"><i class="el-icon-link"></i> 运行管理</span></a>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>

import {mapActions} from "vuex";

export default {
  components: {},
  data() {
    return {
      functionCount: 123,
      accountCount: 1200,
      countData: {
        appCount: 12,
        databaseCount: 10,
        genAppCount: 12,
        genDataCount: 87,
      },
      processStateList: [],
      DefineUserCount:0 ,
      DefineProjectCount:0 ,
      searchParams: {
        pageSize: 10000,
        pageNo: 1,
        searchVal: ''
      }
    };
  },mounted() {
    this.getProcessStateCount();
    this.getDefineUserCount();
    this.getProjectList();
  },
  created() {

  },
  methods: {
    ...mapActions('projects', ['getAllProcessStateCount']),
    ...mapActions('projects', ['getAllDefineUserCount']),
    ...mapActions('projects', ['getProjectsList']),

    initCount() {
      dashboardCount().then(res => {
        this.countData = res.data;
      });
    },
    applicationManger() {
      this.$router.push({ path: `/projects/list` })
    },
    applyApi() {
      this.$router.push({ path: '/com/alinesno/cloud/business/buildGit/list' })
    },
    runManger() {
      this.$router.push({ path: `/monitor/servers/worker` })
    },
    getProcessStateCount()
    {
      this.getAllProcessStateCount().then(res => {
        this.processStateList = [];
        this.processStateList = res.data.taskCountDtos ;
      }).catch(e => {
        this.msg = e.msg || 'error'
      })
    },getDefineUserCount(){
      this.getAllDefineUserCount().then(res => {
        this.DefineUserCount = res.data.count ;
      }).catch(e => {
        this.msg = e.msg || 'error'
      })
    },
    getProjectList() {
      this.getProjectsList(this.searchParams).then(res => {
        this.DefineProjectCount = res.total ;
      }).catch(e => {
        this.msg = e.msg || 'error'
      })
    }

  },
};
</script>

<style scoped lang="scss">

.data-design-img {
    width: calc(100% - 10px);
    margin-top: 10px;
}

.data-card {
  border: 1px solid #e3e4e6;
  background: #fff;
  border-radius: 2px;
  padding: 20px;
  float: left;
  width: 100%;
  margin-bottom: 10px;

  .title {
    margin-top: 0px;
    margin-bottom: 10px;
    font-size: 16px;
    font-weight: 500;
  }

  ul.count-list {
    list-style: none;
    float: left;
    padding: 0px;
    margin: 0px;
    width: 100%;

    li.count-data {
      float: left;
      background: #f7f9fa;
      border-color: #f7f9fa;
      flex: 1;
      height: auto;
      margin-bottom: 10px;
      position: relative;
      text-align: left;
      margin-right: 8px;
      padding: 12px;
      width: calc(50% - 8px);
      text-decoration: none;
      overflow: hidden;
      border-width: 1px;
      border-style: solid;
      border-radius: 2px;
      transition: all 0.1s linear;
      text-align: left;

      span.label-tip {
        width: 100%;
        float: left;
        font-size: 12px;
      }

      span.label-number {
        color: #333;
        display: block;
        float: left;
        line-height: 36px;
        font-size: 20px;
      }
    }
  }
}

.rbac-data-card {
  border: 1px solid #e3e4e6;
  background: #fff;
  border-radius: 2px;
  padding: 20px;
  float: left;
  width: 100%;
  margin-bottom: 20px;

  .title {
    margin-top: 0px;
    margin-bottom: 10px;
    font-size: 16px;
    font-weight: 500;
  }

  .childAccount {
    background: #f7f9fa;
    padding: 8px;
    color: #565050;
    word-break: break-all;
    font-size: 12px;
    margin-top: 20px;
    float: left;
    width: 100%;
    line-height: 15px;
  }

  ul.count-list {
    list-style: none;
    float: left;
    padding: 0px;
    margin: 0px;
    width: 100%;

    li.count-data {
      float: left;
      background: #f7f9fa;
      border-color: #f7f9fa;
      flex: 1;
      height: auto;
      position: relative;
      text-align: left;
      margin-right: 8px;
      padding: 12px;
      width: calc(50% - 8px);
      text-decoration: none;
      overflow: hidden;
      border-width: 1px;
      border-style: solid;
      border-radius: 2px;
      transition: all 0.1s linear;
      text-align: left;

      span.label-tip {
        width: 100%;
        float: left;
        font-size: 12px;
      }

      span.label-number {
        color: #333;
        display: block;
        line-height: 36px;
        font-size: 20px;
        float: left;
      }
    }
  }

  ul.quickEntry {
    display: flex;
    justify-content: space-between;
    padding: 0px;
    margin: 0px;
    float: left;
    width: 100%;
    font-size: 12px;
    margin-top: 20px;

    li {

      display: inline-block;
      flex-grow: 1;
      flex-basis: 0;
      border: 1px solid #c0c6cc;
      text-align: center;
      margin-right: 5px;
      border-radius: 2px;
      font-size: 12px;
      font-weight: 600;
      color: #333;
      height: 33px;
      line-height: 33px;

      a {
        color: #333;
        cursor: pointer;
      }
    }
  }
}
</style>



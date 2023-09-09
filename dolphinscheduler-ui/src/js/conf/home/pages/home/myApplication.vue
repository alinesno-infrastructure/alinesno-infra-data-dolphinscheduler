<template>
  <div class="flow-applist">
    <div v-loading="loading" class="template-list">
      <div v-if="projectsList.length > 0">
        <div class="card-item" v-for="(item, index) in projectsList" :key="index" @click="_switchProjects(item)">
          <div class="item-icon">
            <div class="app-icon">
              <img :src="showAppIcon(index)" class="icon-img" />
            </div>
          </div>
          <div class="item-cont">
            <div class="item-title">
              {{ item.name }}
            </div>
            <div class="item-desc" v-if="item.description">
              {{ item.description }}
            </div>
            <div class="item-desc" v-if="!item.description">
              {{ item.name }}
            </div>
          </div>
        </div>

        <div class="card-item" @click="_createProject()">
          <div class="item-icon">
            <div class="app-icon">
              <img src="http://data.linesno.com/default_add.png" class="icon-img" />
            </div>
          </div>
          <div class="item-cont">
            <div class="item-title">
              创建应用
            </div>
          </div>
        </div>

      </div>

      <div v-else>
        <div class="item-blank" @click="applicationManger()"> <i class="el-icon-s-promotion"></i> 未创建应用，创建一个</div>
      </div>

    </div>

    <el-dialog
      :title="item ? $t('Edit') : $t('Create Project')"
      v-if="createProjectDialog"
      :visible.sync="createProjectDialog"
      width="auto">
      <m-create-project :item="item" @_onUpdate="_onUpdate" @close="_close"></m-create-project>
    </el-dialog>


  </div>
</template>

<script>

import {mapActions} from "vuex";
import mCreateProject from '@/conf/home/pages/projects/pages/list/_source/createProject';

export default {
  name: "index",
  components: { mCreateProject },
  data() {
    return {
      loading: false,
      searchParams: {
        pageSize: 19,
        pageNo: 1,
        searchVal: ''
      },
      projectsList: [],
      createProjectDialog:false,
    };
  },
  watch: {
    createProjectDialog: function (val) {
      if ( !val ) {
        this._getProjectList ();
      }

    }
  }
  ,mounted() {
    this._getProjectList ();
  },
  created() {
  },
  methods: {
    ...mapActions('projects', ['getProjectsList']),
    _getProjectList () {
      this.isLoading = true ;
      this.getProjectsList(this.searchParams).then(res => {
          this.projectsList = []
          this.projectsList = res.totalList
          this.isLoading = false
      }).catch(e => {
        this.isLoading = false
      })
    },_switchProjects (item) {
      this.$router.push({ path: `/projects/${item.code}/index` })
    },
    addService(){
      this.$router.push({ path: `/projects/list` })
    },
    showAppIcon(index) {
      let number = index % 10;
      return require("./deskicon/" + (number + 2) + ".png");
    },
    _createProject (item) {
      this.createProjectDialog = true
      this.item = item
    },
    _onUpdate () {
      this.createProjectDialog = false
      this._debounceGET()
    },
    _close () {
      this.createProjectDialog = false
    }
  },
};
</script>

<style scoped lang="scss">
.flow-applist {
  overflow-x: hidden;
  height: auto;
  padding: 0px 10px 10px 10px;
  background-color: #f7f9fa;

  .template-list {
    margin-right: -30px;
    min-height: 270px;

    .item-blank {
      margin: auto;
      text-align: center;
      padding-top: 140px;
      font-size: 13px;
      cursor: pointer;
    }

    .card-item {
      float: left;
      width: calc(20% - 18px);
      margin-right: 16px;
      margin-top: 16px;
      padding: 9px 16px;
      background: #fff;
      border-radius: 3px;
      cursor: pointer;
      display: flex;
      align-items: center;

      .item-cont {
        overflow: hidden;
      }

      .item-title {
        font-size: 13px;
        color: #333;
        font-weight: 500;
        overflow: hidden; //超出的文本隐藏
        text-overflow: ellipsis; //溢出用省略号显示
        white-space: nowrap; // 默认不换行；
      }

      .item-desc {
        font-size: 12px;
        line-height: 20px;
        color: rgba(25, 31, 37, 0.56);
        text-overflow: ellipsis;
        white-space: nowrap;
        overflow: hidden;
      }

      .item-icon {
        margin-right: 8px;
      }

      .app-icon {
        width: 32px;
        height: 32px;
        position: relative;

        .icon-img {
          width: 100%;
          height: 100%;
          border-radius: 2px;
        }
      }
    }
  }
}
</style>



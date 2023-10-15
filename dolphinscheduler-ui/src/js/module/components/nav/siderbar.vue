<template>
  <div class="siderbar">
    <el-menu
      default-active="1-4-1"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      :collapse="isCollapse"
    >
      <el-menu-item index="1" @click="jumpTo('/home')">
        <i class="el-icon-monitor"></i>
        <span slot="title">{{ $t('Home') }}</span>
      </el-menu-item>

      <el-menu-item index="2" @click="jumpTo('/projects')">
        <i class="el-icon-bank-card"></i>
        <span slot="title">{{ $t('Project Manage') }}</span>
      </el-menu-item>

      <el-menu-item index="6" @click="jumpTo('/resource')">
        <i class="el-icon-copy-document"></i>
        <span slot="title">{{ $t('Resources manage') }}</span>
      </el-menu-item>

      <el-menu-item index="3" @click="jumpTo('/datasource')">
        <i class="el-icon-news"></i>
        <span slot="title">{{ $t('Datasource manage') }}</span>
      </el-menu-item>

      <el-menu-item index="4" @click="jumpTo('/monitor')" v-ps="['ADMIN_USER']">
        <i class="el-icon-pie-chart"></i>
        <span slot="title">{{ $t('Monitor') }}</span>
      </el-menu-item>

      <el-menu-item index="5" @click="jumpTo('/security')" v-ps="['ADMIN_USER']">
        <i class="el-icon-first-aid-kit"></i>
        <span slot="title">{{ $t('Security') }}</span>
      </el-menu-item>

    </el-menu>

    <transition name="fade">
      <div
        class="v-modal sider-modal"
        tabindex="0"
        style="z-index: 2 !important"
        v-if="modalShow"
      ></div>
    </transition>
  </div>
</template>

<script>
// import ProductList from "./ProductList";

  export default {
    components: {
    // ProductList,
    },
    data () {
      return {
        drawer: false,
        modalShow: false,
        direction: 'ltr',
        isCollapse: true
      }
    },
    created () {
      // 添加键盘Esc事件
      this.$nextTick(() => {
        document.addEventListener('keyup', (e) => {
          if (e.keyCode === 27) {
            this.drawer = false
            this.modalShow = false
          }
        })
      })
    },
    methods: {
      openDrawer () {
        console.log('open drawer')
        this.drawer = true
        this.modalShow = true
      },
      handleOpen (key, keyPath) {
        console.log(key, keyPath)
      },
      jumpTo (page) {
        this.$router.push(page)
      },
      handleClose (done) {
        this.modalShow = false
        done()
      /*
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
        */
      }
    }
  }
</script>

<style lang="scss" scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

.siderbar {
  float: left;
  height: 100vh;
  width: 60px;
  border-right: 1px solid #e6e6e6;
  padding-top: 40px;
  overflow: hidden;
  position: fixed;
  z-index: 100;
}

.product-drawer {
  top: 50px;
  z-index: 9 !important ;

  .el-drawer {
    box-shadow: none !important ;
  }
}

.cf-service-box {
  margin-top: 18px;
  overflow: auto;
  position: relative;
  height: calc(100% - 76px);
}

.cf-service-content {
  width: 100%;
  height: calc(100% - 94px);
  overflow-x: hidden;
  overflow-y: auto;
  padding-right: 15px;
  position: relative;
  top: 20px;
}

.cf-service-section {
  width: 200px;
  position: relative;
  float: left;
  margin-bottom: 20px;
  margin-left: 20px;
}

.cf-service-nav-item-title {
  font-size: 13px;
  font-weight: 700;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.cf-service-nav-item {
  position: relative;
  line-height: 30px;
  height: 30px;
  width: 100%;
  cursor: pointer;
  border-bottom: 1px solid transparent;
}

li {
  list-style: none;
}

.cf-service-nav-item a {
  color: #252b3a;
  font-size: 13px;
  text-decoration: none !important;
  display: inline-block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 90%;
}

blockquote,
body,
button,
dd,
div,
dl,
dt,
form,
h1,
h2,
h3,
h4,
h5,
h6,
input,
li,
ol,
p,
pre,
td,
textarea,
th,
ul {
  margin: 0;
  padding: 0;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}

.cf-service-section {
  float: left;
}

.fade-enter {
  opacity: 0;
}
.fade-enter-active {
  transition: opacity 0.5s;
}

.sider-modal {
  background: var(--cb-color-bg-backdrop, rgba(0, 0, 0, 0.2)) !important;
}
</style>

<style lang="scss">
.drawer-box {
  box-shadow: none !important ;
}
</style>
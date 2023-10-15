/* * Licensed to the Apache Software Foundation (ASF) under one or more *
contributor license agreements. See the NOTICE file distributed with * this work
for additional information regarding copyright ownership. * The ASF licenses
this file to You under the Apache License, Version 2.0 * (the "License"); you
may not use this file except in compliance with * the License. You may obtain a
copy of the License at * * http://www.apache.org/licenses/LICENSE-2.0 * * Unless
required by applicable law or agreed to in writing, software * distributed under
the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR
CONDITIONS OF ANY KIND, either express or implied. * See the License for the
specific language governing permissions and * limitations under the License. */
<template>
  <div class="nav-model">
    <router-link :to="{ path: '/home' }" tag="div" class="logo-box">
      <a href="javascript:" :style="'background: url(' + saasLogoUrl + ') no-repeat;'">
        <span> {{ saasTitle }} </span>
      </a>
    </router-link>

    <el-button
      class="dashboard-home"
      size="small"
      icon="el-icon-s-home"
      @click="_dashboardHome()"
    >
      工作台
    </el-button>

    <div class="right">
      <!--
      <span class="lang">
        <el-dropdown @command="_toggleLanguage">
          <span class="el-dropdown-link">
            {{activeLocale.name}}<em class="el-icon-arrow-down"></em>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item :command="item.code" v-for="(item,$index) in localeList" :key="$index">{{item.name}}</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </span>
      -->
      <el-dropdown @command="_toggleUser">
        <span class="el-dropdown-link">
          <em class="el-icon-user"></em>
          &nbsp;
          {{ userInfo.nickName }} <em class="el-icon-arrow-down"></em>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="user">
            <em class="el-icon-user"></em>
            <span>{{ $t('User Information') }}</span>
          </el-dropdown-item>
          <el-dropdown-item command="logout">
            <em class="el-icon-switch-button"></em>
            <span>{{ $t('Logout') }}</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>

    <div class="nav-box">
      <div class="clearfix list">
        <div class="nav-links">
          <router-link :to="{ path: '/home' }" tag="a" active-class="active">
            <span><i class="el-icon-monitor"></i> 工作台</span
            ><strong></strong>
          </router-link>
        </div>
      </div>
      <div class="clearfix list">
        <div class="nav-links">
          <router-link
            :to="{ path: '/service' }"
            tag="a"
            active-class="active"
          >
            <span><i class="el-icon-link"></i> 技术支持</span>
              <strong></strong>
          </router-link>
        </div>
      </div>
    </div>

    
    <div class="file-update-model" @click="_toggleArchive" v-if="isUpdate">
      <div class="icon-cloud">
        <em class="ans el-icon-upload"></em>
      </div>
      <div class="progress-box">
        <m-progress-bar
          :value="progress"
          text-placement="bottom"
        ></m-progress-bar>
      </div>
    </div>
    <div class="adaptive-m-nav">
      <div class="m-nav-box">
        <a href="javascript:" @click="mIsNav = !mIsNav"
          ><em class="ansfont ri-database-2-line"></em
        ></a>
      </div>
      <div class="m-title-box">
        <div class="logo-m"></div>
      </div>
      <div class="m-user-box">
        <a href="javascript:" @click="_goAccount"
          ><em class="el-icon-user"></em
        ></a>
      </div>
      <transition name="slide-fade">
        <div class="m-nav-list" v-if="mIsNav">
          <ul @click="mIsNav = false">
            <router-link :to="{ path: '/home' }" tag="li" active-class="active">
              <em class="ansfont ri-home-4-line"></em>
              <span>{{ $t('Home') }}</span>
            </router-link>
            <router-link
              :to="{ path: '/projects' }"
              tag="li"
              active-class="active"
            >
              <em class="el-icon-tickets"></em>
              <span>{{ $t('Project manage') }}</span>
            </router-link>
            <router-link
              :to="{ path: '/resource' }"
              tag="li"
              active-class="active"
            >
              <em class="el-icon-folder"></em>
              <span>{{ $t('Resources manage') }}</span>
            </router-link>
            <router-link
              :to="{ path: '/datasource' }"
              tag="li"
              active-class="active"
            >
              <em class="ansfont ri-database-2-line"></em>
              <span>{{ $t('Datasource manage') }}</span>
            </router-link>
            <router-link
              :to="{ path: '/security' }"
              tag="li"
              active-class="active"
              v-ps="['ADMIN_USER']"
            >
              <em class="ansfont ri-shield-check-line"></em>
              <span>{{ $t('Security') }}</span>
            </router-link>
          </ul>
        </div>
      </transition>
    </div>

    <el-dialog
      :visible.sync="definitionUpdateDialog"
      append-to-body="true"
      width="auto"
    >
      <m-definition-update
        :type="type"
        @onProgressDefinition="onProgressDefinition"
        @onUpdateDefinition="onUpdateDefinition"
        @onArchiveDefinition="onArchiveDefinition"
        @closeDefinition="closeDefinition"
      ></m-definition-update>
    </el-dialog>

    <el-dialog
      :visible.sync="fileUpdateDialog"
      append-to-body="true"
      width="auto"
    >
      <m-file-update
        :type="type"
        @onProgressFileUpdate="onProgressFileUpdate"
        @onUpdateFileUpdate="onUpdateFileUpdate"
        @onArchiveDefinition="onArchiveFileUpdate"
        @closeFileUpdate="closeFileUpdate"
      ></m-file-update>
    </el-dialog>

    <el-dialog
      :visible.sync="fileChildUpdateDialog"
      append-to-body="true"
      width="auto"
    >
      <m-file-child-update
        :type="type"
        :id="id"
        @onProgressFileChildUpdate="onProgressFileChildUpdate"
        @onUpdateFileChildUpdate="onUpdateFileChildUpdate"
        @onArchiveFileChildUpdate="onArchiveFileChildUpdate"
        @closeFileChildUpdate="closeFileChildUpdate"
      ></m-file-child-update>
    </el-dialog>

    <el-dialog
      :visible.sync="resourceChildUpdateDialog"
      append-to-body="true"
      width="auto"
    >
      <m-resource-child-update
        :type="type"
        :id="id"
        @onProgressResourceChildUpdate="onProgressResourceChildUpdate"
        @onUpdateResourceChildUpdate="onUpdateResourceChildUpdate"
        @onArchiveFileChildUpdate="onArchiveResourceChildUpdate"
        @closeResourceChildUpdate="closeResourceChildUpdate"
      ></m-resource-child-update>
    </el-dialog>
  </div>
</template>
<script>
  import _ from 'lodash'
  import cookies from 'js-cookie'
  import io from '@/module/io'
  import { mapState, mapActions } from 'vuex'
  import { findComponentDownward } from '@/module/util/'
  import mFileUpdate from '@/module/components/fileUpdate/fileUpdate'
  import mFileChildUpdate from '@/module/components/fileUpdate/fileChildUpdate'
  import mResourceChildUpdate from '@/module/components/fileUpdate/resourceChildUpdate'
  import mDefinitionUpdate from '@/module/components/fileUpdate/definitionUpdate'
  import mProgressBar from '@/module/components/progressBar/progressBar'
  import { findLocale, localeList } from '@/module/i18n/config'

  export default {
    name: 'roof-nav',
    data () {
      // TODO 待处理获取到值的问题
      // const { VUE_APP_SASS_TITLE, VUE_APP_SASS_URL, VUE_APP_SAAS_LOGO_URL,VUE_APP_ENABLE_LOGO,VUE_APP_DISPLAY_URL } = process.env

      let saasTitle = 'AIP智能设施'
      let enableLogo = true
      let saasUrl = 'http://alinesno-infra-plat-console-admin.beta.plat.infra.linesno.com'
      let saasLogoUrl = 'http://portal.infra.linesno.com/logo.png'
      let displayUrl = 'http://alinesno-storage.admin.beta.linesno.com/storage/displayImg/'

      // if (VUE_APP_SASS_TITLE) {
      //   saasTitle = VUE_APP_SASS_TITLE
      // }
      // if (VUE_APP_SASS_URL) {
      //   saasUrl = VUE_APP_SASS_URL
      // }
      // if (VUE_APP_SAAS_LOGO_URL) {
      //   saasLogoUrl = VUE_APP_SAAS_LOGO_URL
      // }
      // if (VUE_APP_ENABLE_LOGO === 'off') {
      //   enableLogo = false;
      // }
      // if(VUE_APP_DISPLAY_URL){
      //   displayUrl = VUE_APP_DISPLAY_URL ;
      // }

      return {
        saasTitle,
        saasUrl,
        saasLogoUrl,
        enableLogo,
        displayUrl,
        // Whether to drag
        isDrag: false,
        // Upload progress
        progress: 0,
        // Whether to upload
        isUpdate: false,
        // Whether to log in
        isLogin: false,
        // Mobile compatible navigation
        mIsNav: false,
        // Take the language list data to get rid of the language pack
        localeList: _.map(_.cloneDeep(localeList()), (v) =>
          _.omit(v, ['locale'])
        ),
        // Selected language
        activeLocale: '',
        // Environmental variable
        docLink: '',
        type: '',
        definitionUpdateDialog: false,
        fileUpdateDialog: false,
        fileChildUpdateDialog: false,
        id: null,
        resourceChildUpdateDialog: false
      }
    },
    // created () {
    // 获取企业信息
    // getEnterpriseInfo().then((res) => {
    //   console.info('res = ' + res)

    //   if (res.data && res.data.id) {
    //     this.saasTitle = res.data.name

    //     console.log(this.saasLogoUrl)
    //     console.log('displayUrl ' + this.displayUrl)

    //     // 官网域名
    //     this.domainName = res.data.domainName

    //     if (res.data.logo && this.displayUrl) {
    //       this.saasLogoUrl = this.displayUrl + res.data.logo
    //     }
    //   }
    // })
    // },
    methods: {
      ...mapActions('user', ['signOut']),
      _dashboardHome () {
        window.location.href = this.saasUrl
      },
      /**
       * User Info
       */
      _goAccount () {
        this.isLogin = false
        this.$router.push({ name: 'account' })
      },
      /**
       * _toggle User
       */
      _toggleUser (command) {
        if (command === 'user') {
          this._goAccount()
        } else {
          this._signOut()
        }
      },
      /**
       * Upload (for the time being)
       */
      _fileUpdate (type) {
        if (this.progress) {
          this._toggleArchive()
          return
        }
        this.type = type
        if (this.type === 'DEFINITION') {
          this.definitionUpdateDialog = true
        } else {
          this.fileUpdateDialog = true
        }
      },
      onProgressDefinition (val) {
        this.progress = val
      },
      onUpdateDefinition () {
        let self = this
        findComponentDownward(self.$root, 'definition-list-index')._updateList()
        this.isUpdate = false
        this.progress = 0
        this.definitionUpdateDialog = false
      },

      onArchiveDefinition () {
        this.isUpdate = true
      },

      closeDefinition () {
        this.progress = 0
        this.definitionUpdateDialog = false
      },

      onProgressFileUpdate (val) {
        this.progress = val
      },
      onUpdateFileUpdate () {
        let self = this
        findComponentDownward(
          self.$root,
          `resource-list-index-${this.type}`
        )._updateList()
        this.isUpdate = false
        this.progress = 0
        this.fileUpdateDialog = false
      },
      onArchiveFileUpdate () {
        this.isUpdate = true
      },
      closeFileUpdate () {
        this.progress = 0
        this.fileUpdateDialog = false
      },

      _fileChildUpdate (type, data) {
        if (this.progress) {
          this._toggleArchive()
          return
        }
        this.type = type
        this.id = data
        this.fileChildUpdateDialog = true
      },

      onProgressFileChildUpdate (val) {
        this.progress = val
      },
      onUpdateFileChildUpdate () {
        let self = this
        findComponentDownward(
          self.$root,
          `resource-list-index-${this.type}`
        )._updateList()
        this.isUpdate = false
        this.progress = 0
        this.fileChildUpdateDialog = false
      },

      onArchiveFileChildUpdate () {
        this.isUpdate = true
      },

      closeFileChildUpdate () {
        this.progress = 0
        this.fileChildUpdateDialog = false
      },

      _resourceChildUpdate (type, data) {
        if (this.progress) {
          this._toggleArchive()
          return
        }
        this.type = type
        this.id = data
        this.resourceChildUpdateDialog = true
      },
      onProgressResourceChildUpdate (val) {
        this.progress = val
      },
      onUpdateResourceChildUpdate () {
        let self = this
        findComponentDownward(
          self.$root,
          `resource-list-index-${this.type}`
        )._updateList()
        this.isUpdate = false
        this.progress = 0
        this.resourceChildUpdateDialog = false
      },
      onArchiveResourceChildUpdate () {
        this.isUpdate = true
      },
      closeResourceChildUpdate () {
        this.progress = 0
        this.resourceChildUpdateDialog = false
      },
      /**
       * Upload popup layer display
       */
      _toggleArchive () {
        $('.update-file-modal').show()
      },
      /**
       * sign out
       */
      _signOut () {
        this.signOut()
      },
      /**
       * getEnteripriseInfo
       */
      _enteripriseInfo () {
        return new Promise((resolve, reject) => {
          io.get('dashboard/enteripriseInfo', {}, (res) => {
            resolve(res.data)
          }).catch((e) => {
            reject(e)
          })
        })
      },
      /**
       * Language switching
       */
      _toggleLanguage (language) {
        console.log(language)
        cookies.set('language', language, { path: '/' })
        setTimeout(() => {
          window.location.reload()
        }, 100)
      }
    },
    created () {
      let language = cookies.get('language')
      this.activeLocale = language ? findLocale(language) : '中文'
      this.docLink =
        process.env.NODE_ENV === 'true'
          ? 'docs'
        : `/view/docs/${this.activeLocale.code}/_book` // eslint-disable-line

      // 获取企业信息
      this._enteripriseInfo().then((data) => {
        console.log('res = ' + data + ' , data = ' + data)

        if (data && data.id) {
          this.saasTitle = data.name

          console.log(this.saasLogoUrl)
          console.log('displayUrl ' + this.displayUrl)

          // 官网域名
          this.domainName = data.domainName

          if (data.logo && this.displayUrl) {
            this.saasLogoUrl = this.displayUrl + data.logo
          }
        }
      })
    },
    computed: {
      ...mapState('user', ['userInfo'])
    },
    components: {
      mFileUpdate,
      mProgressBar,
      mDefinitionUpdate,
      mFileChildUpdate,
      mResourceChildUpdate
    }
  }
</script>

<style lang="scss" rel="stylesheet/scss">
.nav-model {
  height: 40px;
  background: var(--cb-color-bg-primary, #fff);
  border-bottom: var(--card-border-width, 1px) var(--card-border-style, solid) var(--card-border-color, #e3e4e6);
  position: relative;
  .m-title-box {
    text-align: center;
    .logo-m {
      width: 36px;
      height: 36px;
      margin: 0 auto;
      position: relative;
      top: 12px;
    }
  }

  .el-dropdown {
    color: #555;
    font-size: 13px;
    vertical-align: middle;
    line-height: 40px;
    font-weight: 550;
    margin-right: 25px;
  }

  .logo-box {
    position: absolute;
    left: 10px;
    top: 2px;
    cursor: pointer;
    > a {
      width: 180px;
      height: 35px;
      line-height: 30px;
      display: block;
      background: url('./logo.jpeg') no-repeat;
      background-size: 35px !important;

      span {
        font-size: 16px;
        text-shadow: 0 0 0 #000;
        color: #3b5998;
        margin-left: 50px;
        margin-top: 4px;
        font-weight: 600;
        position: absolute;
      }
    }
  }
  .nav-box {
    height: 50px;
    line-height: 50px;
    position: absolute;
    float: right;
    right: 150px;
    top: 0;

    .list {
      width: 96px;
      float: left;
      position: relative;
      cursor: pointer;

      .nav-links {
        height: 40px;
        a {
          position: relative;
          text-decoration: none;
          font-size: 13px;
          color: #222;
          text-align: center;

          span {
            display: block;
            width: 96px;
            line-height: 40px !important;
            color: #555;
            font-weight: 550;

            .ansiconfont {
              vertical-align: -2px;
              font-size: 18px;
              margin-right: 4px;
            }
            .ansfont {
              vertical-align: -5px;
              font-size: 18px;
              margin-right: 4px;
            }
          }
          &:hover {
            span {
              color: #3b5998;
            }
          }
          &.active {
            span {
              color: #2d8bf0;
              i {
                color: #3b5998;
              }
            }
            b {
              height: 2px;
              background: #3b5998;
              display: block;
              margin-top: -2px;
            }
          }
        }
      }
    }
  }
  .right {
    position: absolute;
    right: 0;
    float: right;
    top: 0;
    .ans-poptip {
      min-width: 120px;
    }
    > .lang {
      .ans-poptip {
        min-width: 80px;
      }
    }
    > .docs {
      padding-right: 20px;
      a {
        color: #fff;
        font-size: 13px;
        vertical-align: middle;
        &:hover {
          color: #2d8bf0;
        }
      }
    }
    .current-project {
      height: 60px;
      line-height: 56px;
      display: inline-block;
      margin-right: 16px;
      cursor: pointer;
      i {
        font-size: 18px;
        vertical-align: middle;
        &:nth-child(1) {
          font-size: 20px;
          color: #3b5998;
        }
      }
      span {
        color: #333;
        vertical-align: middle;
        font-size: 13px;
      }
      &:hover {
        span {
          color: #3b5998;
        }
      }
    }
    .login-model {
      display: inline-block;
      margin-right: 20px;
      cursor: pointer;
      margin-top: 16px;
      em {
        font-size: 18px;
        vertical-align: middle;
        color: #fff;
      }
      span {
        color: #fff;
        vertical-align: middle;
        font-size: 13px;
      }
      &:hover {
        > i,
        > span {
          color: #3b5998;
        }
      }
    }
    .lrns-list {
      margin: -6px 0;
      a {
        display: block;
        height: 30px;
        line-height: 30px;
        font-size: 13px;
        &:hover {
          i,
          span {
            color: #3b5998;
          }
        }
      }
    }
  }
  .file-update-model {
    cursor: pointer;
    .progress-box {
      width: 240px;
    }
    .icon-cloud {
      position: absolute;
      left: -34px;
      top: 2px;
      > i {
        font-size: 22px;
        color: #3b5998;
      }
    }
  }
}

.dashboard-home {
  background-color: #f4f6f7;
  border: 0;
  position: absolute;
  margin-left: 180px;
  margin-top: 5px;
  font-weight: 500;
}
</style>

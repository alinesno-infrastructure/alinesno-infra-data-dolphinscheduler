<template>
  <div>
    <el-row class="acp-dashboard-panel" :gutter="20">
      <el-col class="panel-col" :span="19">
        <div class="grid-content">
          <div class="panel-header">
            <div class="header-title"><i class="fa-solid fa-link"></i> 编排计算使用教程</div>
          </div>
          <div class="panel-body" style="height: auto;position: relative;">
              <div class="direct-box">
                <ul>
                  <li class="box-item" v-for="item in directContent" :key="item">
                    <div class="dire-panel">
                      <div class="panel-title">
                        <img class="dire-panel-icon" :src="item.icon" :alt="item.name" />
                        {{ item.name }} 
                      </div>
                      <div class="panel-describe">{{ item.desc }}</div>
                      <div class="panel-tip">访问链接</div>
                    </div>
                  </li>
                </ul>
              </div>
          </div>
        </div>
      </el-col>
      <el-col :span="5">
        <div class="grid-content">
          <div class="panel-header">
            <div class="header-title"><i class="fa-solid fa-link"></i> 构建解决方案</div>
          </div>
          <div class="panel-body" style="height: auto;position: relative;">
            <div class="acp-app-list">
              <ul>
                <li class="app-items" style="width:100%" v-for="item in apps" :key="item">
                  <div class="app-icon">
                    <img :src="item.icon" :alt="item.name" />
                  </div>
                  <div class="app-info">
                    <div class="app-item-title">{{ item.name }} <i class="fa-solid fa-arrow-up-right-from-square"></i>
                    </div>
                    <div class="app-item desc">{{ item.desc }}</div>
                  </div>
                </li>
              </ul>
            </div>
          </div>

        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>

export default {
  data() {
    return {
      directContent: [
      {
        "icon": "https://d1by4p17n947rt.cloudfront.net/icon/d88319dfa5d204f019b4284149886c59-7d586ea82f792b61a8c87de60565133d.svg",
        "name": "快速数据集成迁移",
        "desc": "提供快速且无缝的数据集成迁移服务，帮助用户将数据从一个系统或平台迁移到另一个系统或平台。"
      },
      {
        "icon": "https://d1by4p17n947rt.cloudfront.net/icon/f5d2c00d40914bff4f82f29f9ef768bc-53a84099cf556710383a52b4612a8612.svg",
        "name": "数据离线分析计算",
        "desc": "通过离线分析计算，对大量数据进行处理和分析，以获得有关数据集的洞察和结论。"
      },
      {
        "icon": "https://d1by4p17n947rt.cloudfront.net/icon/3da5e8169d2f1426f99fbef54575fe96-6382cb2dfdd2f74c99bc8a64a338358e.svg",
        "name": "数据分层关系规范",
        "desc": "定义和规范数据的分层关系，确保数据在不同层级之间的正确关联和一致性。"
      },
      {
        "icon": "https://d1by4p17n947rt.cloudfront.net/icon/fb0cde6228b21d89ec222b45efec54e7-0856e92285f4e7ed254b2588d1fe1829.svg",
        "name": "数据生命周期和仓库",
        "desc": "管理数据的完整生命周期，包括数据的创建、存储、使用、归档和销毁等各个阶段，并提供相应的数据仓库支持。"
      },
      {
        "icon": "https://d1by4p17n947rt.cloudfront.net/icon/a5ffe5487f62ef75d8e5cf78c18525a5-d4867f9d4adcd749f0c5aff987232847.svg",
        "name": "轻量级湖仓一体化",
        "desc": "将数据湖和数据仓库相结合，提供一体化的数据存储和管理解决方案，具有轻量级和高效的特点。"
      }
      ],
      apps: [
        {
          "icon": "https://d1by4p17n947rt.cloudfront.net/icon/051de32597041e41f73b97d61c67a13b-9cbdaf85e3bcf29b656fdedd8e6d1305.svg",
          "name": "数据安全备份",
          "desc": "数据安全备份"
        },
        {
          "icon": "https://d1by4p17n947rt.cloudfront.net/icon/fb0cde6228b21d89ec222b45efec54e7-0856e92285f4e7ed254b2588d1fe1829.svg",
          "name": "企业数据迁移",
          "desc": "企业数据迁移"
        },
        {
          "icon": "https://d1by4p17n947rt.cloudfront.net/icon/7177e919b32ad97825f95e902595014b-1594766d92813b5baeb706c453f91de0.svg",
          "name": "数据智能治理方案",
          "desc": "数据智能治理方案"
        },
        {
          "icon": "https://d1by4p17n947rt.cloudfront.net/icon/fb0cde6228b21d89ec222b45efec54e7-0856e92285f4e7ed254b2588d1fe1829.svg",
          "name": "DataOps集成一体",
          "desc": "DataOps集成一体"
        },
        {
          "icon": "https://d1by4p17n947rt.cloudfront.net/icon/9da5a168cf8194c8ee5ed192a443d563-674375b53bc8ae94f48cfdb5c81e8363.svg",
          "name": "数据离线分析",
          "desc": "数据离线分析"
        }
      ]
    };
  }
}
</script>

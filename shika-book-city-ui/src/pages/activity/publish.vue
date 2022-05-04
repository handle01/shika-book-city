<template>
  <!--修改-->
  <el-dialog
    v-model="dialogVisible"
    title="书籍信息"
    width="50%"
    :before-close="handleClose"
    modal-append-to-body="false"
    ><div class="mycontainer">
      <el-form
        ref="ruleFormRef"
        :model="form"
        label-width="100px"
        :rules="rules"
      >
        <el-form-item
          label="活动名称"
          prop="activityName"
          :rules="[
            {
              required: true,
              message: '不能为空',
              trigger: 'blur',
            },
          ]"
        >
          <el-input v-model="form.activityName"></el-input>
        </el-form-item>
       
        <el-row>
           <el-form-item
            prop="price"
            label="价格"
            :rules="[
              {
                required: true,
                message: '不能为空',
                trigger: 'blur',
              },
              {
                type: 'number',
                message: '必须为数字',
                trigger: 'blur',
              },
            ]"
          >
            <el-input v-model.number="form.price"></el-input>
          </el-form-item>
          <div class="demo-date-picker">
              <el-date-picker
                v-model="form.publishTime"
                type="datetimerange"
                range-separator="To"
                start-placeholder="开始时间"
                end-placeholder="结束时间"
                popper-class="z"
                value-format="YYYY-MM-DD HH:mm:ss"
              />
            </div>
        </el-row>
        <el-row>
          <el-form-item
            pror="imageUrl"
            label="封面"
            :rules="[
              {
                required: true,
                message: '不能为空',
                trigger: 'blur',
              },
            ]"
          >
            <el-upload
              class="avatar-uploader"
              action="/user/upload"
              :auto-upload="autoUpload"
              name="imgFile"
              :headers="headers"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
            >
              <img v-if="imageUrl" :src="imageUrl" class="avatar" />
              <el-icon v-else class="avatar-uploader-icon"
                ><i class="el-icon"
                  ><svg
                    viewBox="0 0 1024 1024"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      fill="currentColor"
                      d="M480 480V128a32 32 0 0 1 64 0v352h352a32 32 0 1 1 0 64H544v352a32 32 0 1 1-64 0V544H128a32 32 0 0 1 0-64h352z"
                    ></path></svg></i
              ></el-icon>
            </el-upload>
          </el-form-item>
          <el-form-item pror="description" label="简介"
            ><el-input type="textarea" v-model="form.description"></el-input>
          </el-form-item>
        </el-row>
        <el-form-item label="所含书籍">
          <el-checkbox-group v-model="form.books">
            <el-checkbox
              v-for="book in allBook"
              :key="book"
              :label="book.id"
              >{{ book.name}}</el-checkbox
            >
          </el-checkbox-group>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="valid(form)">修改</el-button>
          <el-button @click="dialogVisible = false">退出</el-button>
        </el-form-item>
      </el-form>
    </div>
  </el-dialog>

  <el-table :data="filterTableData" style="width: 100%">
    <el-table-column label="封面">
      <template #default="scope">
        <img :src="scope.row.cover" style="height: 50px" />
      </template>
    </el-table-column>

    <el-table-column label="活动名称" prop="activityName" />
    <el-table-column label="价格（单位：元）" prop="price" />
     <el-table-column label="开始时间" prop="startTime" />
    <el-table-column label="截止时间" prop="activityDeadline" />

    <el-table-column align="right">
      <template #header>
        <el-input v-model="search" size="small" placeholder="Type to search" />

      </template>
      <template #default="scope">
        <el-button size="small" @click="handleEdit(scope.row)">修改</el-button>

        <el-button size="small" type="danger" @click="Delete(scope.row.id)"
          >强制取消</el-button
        >
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
    small
    background
    layout="prev, pager, next"
    :total="total"
    class="mt-4"
    :page-size="pageSize"
    @current-change="changPage"
  />
</template>
<script  setup>
import { ElMessage, ElMessageBox } from "element-plus";
import { computed, ref,onMounted } from "vue";
import { getAllBook,getPending, updateConfig,delActivity} from "@/api/activity";

//上传文件携带请求头认证
let headers = ref({
  token: storage.getSessionString("token"),
  userName: storage.getSessionString("userName"),
});






//是否显现
var dialogVisible = ref();

const ruleFormRef = ref(null);

var addForm = ref({}); //新增表单
var form = ref({}); //表单
var tableData = ref([]); //列表数据
var total = ref(0); //数据总条数
var pageSize = ref(0); //页面最大展现条数
let imageUrl = ref(); //封面图片
import storage from "@/util/storage";

let get = ref({
  size: 10,
  current: 1,
  userName: storage.getSessionString("userName"),
});
//书籍数据
let allBook = ref([])
//页面跳转渲染数据
onMounted(() => {

  getPending(get.value).then((res) => {
    tableData.value = res.data.data.list;
    pageSize.value = res.data.data.pageSize;
    total.value = res.data.data.total;
  });
  let data = ref({
userName: storage.getSessionString("userName")
  })
  getAllBook(data.value).then(res=>{
    allBook.value = res.data.data
  })
});
//上传完成后执行
let handleAvatarSuccess = function (response) {
  imageUrl.value = response.data;
  form.value.cover = response.data;
  addForm.value.cover = response.data;
  ElMessage({
    showClose: true,
    message: response.message,
    type: "success",
  });
};
//上传前校验
let beforeAvatarUpload = function (rawFile) {
  const isJPG = rawFile.type === "image/jpeg";
  const isLt2M = rawFile.size / 1024 / 1024 < 2;
  if (!isJPG) {
    ElMessage({
      showClose: true,
      message: "上传头像图片只能是 JPG 格式!",
      type: "error",
    });
  }
  if (!isLt2M) {
    ElMessage({
      showClose: true,
      message: "上传头像图片大小不能超过 2MB!",
      type: "error",
    });
  }
  return isJPG && isLt2M;
};

let currentPage = 1;
//切换页
let changPage = function (page) {
  currentPage = page;
  getPending({
    current: page,
    size: 10,
    userName: storage.getSessionString("userName"),
  }).then((res) => {
    tableData.value = res.data.data.list;
  });
};


let search = ref("");
//搜索
let filterTableData = computed(() =>
  tableData.value.filter(
    (data) =>
      !search.value ||
      data.name.toLowerCase().includes(search.value.toLowerCase())
  )
);
//修改
const handleEdit = (from) => {
  form.value = from;
  imageUrl.value = from.cover;
  dialogVisible.value = !dialogVisible.value;
};


//删除
const Delete = (data) => {
  ElMessageBox.confirm("是否要强制停止该活动?", "提示", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      var id = {
        bookId: data,
        userId: storage.getSessionString("id"),
      };
      delActivity(id).then((res) => {
        if (res.data.flag === true) {
          ElMessage.success(res.data.message);
          getPending({
            size: 10,
            current: currentPage,
            userName: storage.getSessionString("userName"),
          }).then((res) => {
            tableData.value = res.data.data.list;
            pageSize.value = res.data.data.pageSize;
            total.value = res.data.data.total;
          });
        } else {
          ElMessage.error(res.data.message);
        }
      });
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "取消成功",
      });
    });
};

//提交表单
const valid = (form) => {
  // !Number.isInteger(value)
  ruleFormRef.value.validate((valid) => {
    if (valid) {
      updateConfig(form).then((res) => {
        if (res.data.flag === true) {
          ElMessage({
            showClose: true,
            message: res.data.message,
            type: "success",
          });
          dialogVisible.value = !dialogVisible.value;
          getPending({
            size: 10,
            current: currentPage,
            userName: storage.getSessionString("userName"),
          }).then((res) => {
            tableData.value = res.data.data.list;
            pageSize.value = res.data.data.pageSize;
            total.value = res.data.data.total;
          });
        } else {
          ElMessage({
            showClose: true,
            message: res.data.message,
            type: "error",
          });
        }
      });
    } else {
      ElMessage.error("表单信息不全");
      return false;
    }
  });
};
</script>
<style >
.z {
  z-index: 10001 !important;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.el-input__inner {
  -webkit-appearance: none;
  background-color: var(--el-input-bg-color, var(--el-fill-color-blank));
  background-image: none;
  border-radius: var(--el-input-border-radius, var(--el-border-radius-base));
  box-sizing: border-box;
  color: var(--el-input-text-color, var(--el-text-color-regular));
  display: inline-block;
  font-size: inherit;
  height: 32px;
  line-height: 32px;
  outline: 0;
  padding: 0 11px;
  transition: var(--el-transition-box-shadow);
  width: 500%;
  box-shadow: 0 0 0 1px var(--el-input-border-color, var(--el-border-color))
    inset;
  border: none;
}
.avatar-uploader .el-upload {
  width: 120px;
  height: 120px;
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}
</style>

